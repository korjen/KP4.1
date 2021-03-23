package by.bsuir.facade;

import by.bsuir.ApplicationProperties;
import by.bsuir.converter.PassportConverter;
import by.bsuir.converter.UserConverter;
import by.bsuir.converter.WorkContractConverter;
import by.bsuir.converter.WorkerConverter;
import by.bsuir.dao.PassportDAOImpl;
import by.bsuir.dto.WorkerDTO;
import by.bsuir.model.*;
import by.bsuir.service.interfaces.*;
import by.bsuir.utility.Deduction;
import by.bsuir.utility.DeductionStatic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class WorkerOperationsFacade {
    private UserService userService;
    private PassportService passportService;
    private WorkContractService workContractService;
    private WorkerService workerService;
    private SalaryService salaryService;
    private WorkingTimeService workingTimeService;
    private PrivilegeService privilegeService;
    private ApplicationProperties applicationProperties;
    @Autowired
    private StartApplicationFacade facade;

    public boolean checkNewWorker(Worker worker) {
        WorkContract workContract = workContractService.findById(worker.getContract().getContractNumber());
        if (workContract!=null) return false;
        else return true;
    }

    public int checkNewUser(User user) {
        WorkContract workContract = workContractService.findById(user.getWorker().getContract().getContractNumber());
        if (userService.findById(user.getLogin())!=null) return 1;
        if (workContract!=null) {
            if(facade.isContractExpired(workContract.getEndDate())) return -3;
            User tmp = workContract.getWorker().getUser();
            if(tmp==null) return 0;
            else return -2;
        }
        else return -1;
    }

    public ModelAndView addWorker(final Worker worker) {
        checkPrivileges(worker);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        User user = userService.findById(currentUser);
        if (facade.isContractExpired(worker.getContract().getEndDate())) worker.setActive(false);
        else worker.setActive(true);
        worker.getContract().setWorker(worker);
        worker.getPassport().setWorker(worker);
        workContractService.save(worker.getContract());
        workerService.save(worker);
        passportService.save(worker.getPassport());
        if (user.getRole().equals("ROLE_ADMIN")) return new ModelAndView("redirect:/admin/workers/list");
        else if (user.getRole().equals("ROLE_MANAGER")) return new ModelAndView("redirect:/manager/workers/list");
        return createWorkerListPage(1);
    }

    public ModelAndView addUser(final User user) {
        WorkContract workContract = workContractService.findById(user.getWorker().getContract().getContractNumber());
        user.setWorker(workContract.getWorker());
        userService.save(user);
        return new ModelAndView("redirect:/admin/users/list");
    }

    public void setPrivileges() {
        List<Worker> workers = workerService.getAll();
        for (Worker worker : workers) {
            checkPrivileges(worker);
            workerService.update(worker);
        }
    }

    public void checkPrivileges(Worker worker) {
        List<Privilege> privileges = worker.getPrivileges();
        boolean alreadyHasPrivilege=false;
        if (privileges.size()!=0) {
            for (Privilege element : privileges) {
                element.getWorkers().clear();
            }
            privileges.clear();
        }
        if (worker.isSoloParent() && worker.getNumberOfDependants()>0) {
            Privilege existingPrivilege = privilegeService.findById(2);
            worker.getPrivileges().add(existingPrivilege);
        }
        else if (!worker.isSoloParent() && worker.getNumberOfDependants()>0) {
            Privilege existingPrivilege = privilegeService.findById(1);
            worker.getPrivileges().add(existingPrivilege);
        }
        if (worker.getNumberOfDependants()==0) worker.setSoloParent(false);
        if (worker.getContract().getRate() < 709) {
            alreadyHasPrivilege=true;
            Privilege existingPrivilege = privilegeService.findById(3);
            worker.getPrivileges().add(existingPrivilege);
        }
        if (worker.getSalary().size()>0) {
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            int currentMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
            for (Salary salary : worker.getSalary()) {
                String[] strArr = salary.getPayDate().split("-");
                int year = Integer.parseInt(strArr[0]);
                int month = Integer.parseInt(strArr[1]);
                if (year==currentYear && month == currentMonth) {
                    if (salary.getSum()<709 && !alreadyHasPrivilege) {
                        Privilege existingPrivilege = privilegeService.findById(3);
                        worker.getPrivileges().add(existingPrivilege);
                        break;
                    }
                }
            }
        }
    }

    public ModelAndView updateWorker(final Worker worker) {
        if (facade.isContractExpired(worker.getContract().getEndDate()) && worker.isActive()) {
            worker.setActive(false);
            if(worker.getUser()!=null) worker.setUser(null);
            worker.getContract().setDismissalDate(worker.getContract().getEndDate());
            if(worker.getUser()!=null) userService.delete(worker.getUser().getLogin());
        }
        else if (!facade.isContractExpired(worker.getContract().getEndDate()) && !worker.isActive()) {
            worker.setActive(true);
            worker.getContract().setDismissalDate(null);
            worker.getContract().setOrderNumber(null);
        }
        if (worker.getUser()!=null && worker.getUser().getLogin().isEmpty()) worker.setUser(null);
        checkPrivileges(worker);
        worker.getPassport().setWorker(worker);
        if(worker.getUser()!=null) worker.getUser().setWorker(worker);
        worker.getContract().setWorker(worker);
        passportService.update(worker.getPassport());
        if(worker.getUser()!=null) userService.update(worker.getUser());
        workerService.update(worker);
        workContractService.update(worker.getContract());
        return createWorkerProfilePage(worker.getIdWorker());
    }

    public void deleteWorker(final int workerId) {
        workerService.delete(workerId);
    }

    public ModelAndView activateWorker(int id, String signDate,String endDate) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        User user = userService.findById(currentUser);
        Worker worker = workerService.findById(id);
        worker.getContract().setSignDate(signDate);
        worker.getContract().setEndDate(endDate);
        worker.getContract().setDismissalDate(null);
        worker.getContract().setOrderNumber(null);
        worker.setActive(true);
        workerService.update(worker);
        workContractService.update(worker.getContract());
        if (user.getRole().equals("ROLE_ADMIN")) return new ModelAndView("redirect:/admin/workers/list");
        else if (user.getRole().equals("ROLE_MANAGER")) return new ModelAndView("redirect:/manager/workers/list");
        return createWorkerListPage(1);
    }

    public ModelAndView deactivateWorker(int id, String dismissalDate, String orderNumber) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        User user = userService.findById(currentUser);
        Worker worker = workerService.findById(id);
        worker.getContract().setDismissalDate(dismissalDate);
        worker.getContract().setOrderNumber(orderNumber);
        worker.setActive(false);
        workerService.update(worker);
        workContractService.update(worker.getContract());
        User deactivateUser=null;
        if (worker.getUser()!=null && !worker.getUser().getLogin().isEmpty()) deactivateUser = userService.findById(worker.getUser().getLogin());
        if(deactivateUser!=null) {
            worker.setUser(null);
            userService.delete(worker.getUser().getLogin());
            if (currentUser.equals(deactivateUser.getLogin())) {
                return new ModelAndView("redirect:/exit");
            }
        }
        if (user.getRole().equals("ROLE_ADMIN")) return new ModelAndView("redirect:/admin/workers/list");
        else if (user.getRole().equals("ROLE_MANAGER")) return new ModelAndView("redirect:/manager/workers/list");
        return createWorkerListPage(1);
    }

    @Transactional
    public ModelAndView createWorkerListPage(final int page) {
        ModelAndView mav = new ModelAndView("workerList");
        int recordsPerPage = Integer.valueOf(applicationProperties.getAmountOfRecordsPerPage());
        List<Worker> list = workerService.getAllForPagination((page - 1) * recordsPerPage,
                recordsPerPage);
        int noOfRecords = workerService.getAll().size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        mav.addObject("noOfPages", noOfPages);
        mav.addObject("currentPage", page);
        mav.addObject("workersList", list);
        return mav;
    }

    @Transactional
    public ModelAndView createPaymentHistoryPage(final int page){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        User user = userService.findById(currentUser);
        ModelAndView mav = new ModelAndView("workerPaymentHistory");
        int recordsPerPage = Integer.valueOf(applicationProperties.getAmountOfLessRecordsPerPage());
        List<Salary> list = salaryService.getAllForPaginationByWorker(user.getWorker().getIdWorker(),(page - 1) * recordsPerPage,
                recordsPerPage);
        int noOfRecords = user.getWorker().getSalary().size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        boolean privilege=false;
        if (user.getWorker().getPrivileges().size()>0) privilege=true;
        mav.addObject("hasPrivilege", privilege);
        mav.addObject("noOfPages", noOfPages);
        mav.addObject("currentPage", page);
        mav.addObject("salariesList", list);
        return mav;
    }

    public ModelAndView createPrivilegesPage(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        User user = userService.findById(currentUser);
        ModelAndView mav = new ModelAndView("workerPrivileges");
        List<Privilege> list = user.getWorker().getPrivileges();
        mav.addObject("privilegesList", list);
        return mav;
    }

    public ModelAndView createWorkerProfilePage(final String id) {
        ModelAndView mav = new ModelAndView("workerProfile");
        User user = userService.findById(id);
        Worker worker = user.getWorker();
        mav.addObject("worker", worker);
        return mav;
    }

    public ModelAndView createWorkerPaymentProfilePage(final int id) {
        ModelAndView mav = new ModelAndView("workerPaymentProfile");
        Worker worker = workerService.findById(id);
        mav.addObject("worker", worker);
        return mav;
    }

    public ModelAndView createWorkerProfilePage(final int id) {
        ModelAndView mav = new ModelAndView("workerProfile");
        Worker worker = workerService.findById(id);
        mav.addObject("worker", worker);
        return mav;
    }

    public ModelAndView createUpdatePage(final int id) {
        ModelAndView mav = new ModelAndView("workerUpdate");
        Worker worker = workerService.findById(id);
        mav.addObject("worker", worker);
        return mav;
    }

    @Transactional
    public ModelAndView createEditWorkingTimePage(final int id, final int page, String error) {
        ModelAndView mav = new ModelAndView("workerEditWorkingTime");
        int recordsPerPage = Integer.valueOf(applicationProperties.getAmountOfMoreRecordsPerPage());
        List<WorkingTime> list = workingTimeService.getAllForPaginationByWorker(id,(page - 1) * recordsPerPage,
                recordsPerPage);
        Worker worker  = workerService.findById(id);
        int noOfRecords = worker.getWorkingTime().size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        mav.addObject("noOfPages", noOfPages);
        mav.addObject("currentPage", page);
        mav.addObject("timeList", list);
        if (error!=null) mav.addObject("error", error);
        WorkingTime workingTime = new WorkingTime();
        workingTime.setWorker(worker);
        mav.addObject("time",workingTime);
        return mav;
    }

    public ModelAndView showDeactivationPage(final int id) {
        ModelAndView mav = new ModelAndView("workerDeactivate");
        Worker worker = workerService.findById(id);
        mav.addObject("worker", worker);
        mav.addObject("deactivatedWorker", new WorkerDTO() );
        return mav;
    }

    public ModelAndView showActivationPage(final int id) {
        ModelAndView mav = new ModelAndView("workerActivate");
        Worker worker = workerService.findById(id);
        mav.addObject("worker", worker);
        mav.addObject("activatedWorker", new WorkerDTO() );
        return mav;
    }


    int getVacationDays(Worker worker) {
        if (worker.getWorkingTime().size()!=0) {
            int days = 0;
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            for (WorkingTime workingTime : worker.getWorkingTime()) {
                if (workingTime.getReason()!=null) {
                    String[] strArr = workingTime.getStartTime().split("-");
                    int month = Integer.parseInt(strArr[1]);
                    int year = Integer.parseInt(strArr[0]);
                    if (month == DeductionStatic.getMonth() && year == currentYear) {
                        if (!workingTime.isAttendance() && workingTime.getReason().equals("отпуск"))
                            days++;
                    }
                }
            } return days;
        }
        return 0;
    }

    boolean hasOtherReasonsForAbsence(Worker worker) {
        if (worker.getWorkingTime().size()!=0) {
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            for (WorkingTime workingTime : worker.getWorkingTime()) {
                if (workingTime.getReason()!=null) {
                    String[] strArr = workingTime.getStartTime().split("-");
                    int month = Integer.parseInt(strArr[1]);
                    int year = Integer.parseInt(strArr[0]);
                    if (month == DeductionStatic.getMonth() && year == currentYear) {
                        if (!workingTime.isAttendance() && workingTime.getReason().equals("больничный"))
                            return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    float averagePayment(Worker worker) {
        if (worker.getSalary().size()!=0) {
            float averagePayment=0;
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            List<Salary> thisYearSalaries = new ArrayList<>();
            for (Salary salary : worker.getSalary()) {
                String[] strArr = salary.getPayDate().split("-");
                int year = Integer.parseInt(strArr[0]);
                int month = Integer.parseInt(strArr[1]);
                if (year==currentYear && month!=DeductionStatic.getMonth()) {
                    thisYearSalaries.add(salary);
                }
            }
            if (thisYearSalaries.size() < 12) {
                List<Salary> previousYearSalaries = new ArrayList<>();
                int monthNumber=12;
                int number = 12 - thisYearSalaries.size();
                for (Salary salary : worker.getSalary()) {
                    if (number > 0) {
                        String[] strArr = salary.getPayDate().split("-");
                        int year = Integer.parseInt(strArr[0]);
                        int month = Integer.parseInt(strArr[1]);
                        if (year == (currentYear - 1) && month == monthNumber) {
                            previousYearSalaries.add(salary);
                            monthNumber--;
                            number--;
                        }
                    }
                }
                if (thisYearSalaries.size()+previousYearSalaries.size()==12) {
                for (Salary salary : thisYearSalaries) {
                    averagePayment += salary.getSum();
                }
                for (Salary salary : previousYearSalaries) {
                    averagePayment += salary.getSum();
                }
                return averagePayment / 12;
            } else return 0;
            } else {
                for (Salary salary : thisYearSalaries) {
                    averagePayment += salary.getSum();
                }
                return averagePayment / 12;
            }
        }
        return 0;
    }

    int getWorkingTimeInDays(Worker worker) {
        if (worker.getWorkingTime().size()!=0) {
            int days=0;
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            for (WorkingTime workingTime : worker.getWorkingTime()) {
                String[] strArr = workingTime.getStartTime().split("-");
                int month = Integer.parseInt(strArr[1]);
                int year = Integer.parseInt(strArr[0]);
                if (workingTime.isAttendance() && month== DeductionStatic.getMonth() && year==currentYear) {
                    if ((workingTime.getWorkingHours() < 8 && (8 - workingTime.getWorkingHours())<=0.5)
                            || workingTime.getWorkingHours()>=8)
                    days++;
                }
            }
            return days;
        }
        return 0;
    }

    Salary wasCalculated(Worker worker) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if (worker.getSalary().size()==0) return null;
        for (Salary salary : worker.getSalary()) {
            String[] strArr = salary.getPayDate().split("-");
            int month = Integer.parseInt(strArr[1]);
            int year = Integer.parseInt(strArr[0]);
            if (month== DeductionStatic.getMonth() && year==currentYear) {
                return salary;
            }
        }
        return null;
    }

    public void calculatePayment() {
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String strDate = sdformat.format(now);
        List<Worker> workers = workerService.getAll();
        DecimalFormat df = new DecimalFormat("0.00");
        for (Worker worker : workers) {
            if (worker.getUser()==null) continue;
            boolean wasCalculated = false;
            Salary salary = wasCalculated(worker);
            if (salary!=null) {
                wasCalculated=true;
            }
            else salary = new Salary();
            salary.setPayDate(strDate);
            float tax;
            float effect=0;
            int workingDays = getWorkingTimeInDays(worker);
            int vacationDays=0;
            if(workingDays >= DeductionStatic.getWorkingDays()) {
                if (worker.getPrivileges().size()==0) {
                    salary.setPrivilege(false);
                    tax = worker.getContract().getRate()* DeductionStatic.getTaxPercent() + worker.getContract().getRate()*DeductionStatic.getPensionInsurancePercent();
                } else {
                    salary.setPrivilege(true);
                    for(Privilege privilege : worker.getPrivileges()) {
                        effect+=privilege.getEffect();
                    }
                    tax = (worker.getContract().getRate()-effect)* DeductionStatic.getTaxPercent() + worker.getContract().getRate()*DeductionStatic.getPensionInsurancePercent();
                }
                tax = Float.parseFloat(df.format(tax));
                salary.setWorkingDays(workingDays);
                salary.setTax(tax);
                salary.setSum(worker.getContract().getRate()-tax);
                salary.setWorker(worker);
            } else {
                vacationDays = getVacationDays(worker);
                float payment;
                boolean lowIncome=false, zeroTax=false, alreadyHasPrivilege=false;
                if (vacationDays > 0 && !hasOtherReasonsForAbsence(worker)) {
                    float averagePayment = averagePayment(worker);
                    if (averagePayment > 0) {
                        float vacationPayment = averagePayment/DeductionStatic.getWorkingDays()*vacationDays;
                        payment = worker.getContract().getRate()/DeductionStatic.getWorkingDays()*workingDays;
                        if ((payment+vacationPayment)<709) lowIncome=true;
                        if (worker.getPrivileges().size()==0) {
                            if (!lowIncome) salary.setPrivilege(false);
                            else {
                                salary.setPrivilege(true);
                                worker.getPrivileges().add(privilegeService.findById(3));
                                effect=privilegeService.findById(3).getEffect();
                                workerService.update(worker);
                            }
                            if (effect > (payment+vacationPayment)) zeroTax=true;
                            if (zeroTax) tax = (payment+vacationPayment)*DeductionStatic.getPensionInsurancePercent();
                            else tax = (payment+vacationPayment)* DeductionStatic.getTaxPercent() + (payment+vacationPayment)*DeductionStatic.getPensionInsurancePercent();
                        } else {
                            salary.setPrivilege(true);
                            for(Privilege privilege : worker.getPrivileges()) {
                                if (privilege.getIdPrivilege()==3) alreadyHasPrivilege=true;
                                effect+=privilege.getEffect();
                            }
                            if (!alreadyHasPrivilege && lowIncome) {
                                worker.getPrivileges().add(privilegeService.findById(3));
                                effect+=privilegeService.findById(3).getEffect();
                                workerService.update(worker);
                            }
                            if (effect > (payment+vacationPayment)) zeroTax=true;
                            if (zeroTax) tax = (payment+vacationPayment)*DeductionStatic.getPensionInsurancePercent();
                            else tax = (payment+vacationPayment-effect)* DeductionStatic.getTaxPercent() + (payment+vacationPayment)*DeductionStatic.getPensionInsurancePercent();
                        }
                        tax = Float.parseFloat(df.format(tax));
                        salary.setTax(tax);
                        payment+=vacationPayment;
                        payment-=tax;
                        payment =  Float.parseFloat(df.format(payment));
                        salary.setWorkingDays(workingDays);
                        salary.setVacationDays(vacationDays);
                        salary.setSum(payment);
                        salary.setWorker(worker);
                    }
                }
                else if (workingDays>0 && !hasOtherReasonsForAbsence(worker)) {
                    payment = worker.getContract().getRate()/DeductionStatic.getWorkingDays()*workingDays;
                    if (payment<709) lowIncome=true;
                    if (worker.getPrivileges().size()==0) {
                        if (!lowIncome) salary.setPrivilege(false);
                        else {
                            salary.setPrivilege(true);
                            worker.getPrivileges().add(privilegeService.findById(3));
                            effect=privilegeService.findById(3).getEffect();
                            workerService.update(worker);
                        }
                        if (effect > (payment)) zeroTax=true;
                        if (zeroTax) tax = payment*DeductionStatic.getPensionInsurancePercent();
                        else tax = payment* DeductionStatic.getTaxPercent() + payment*DeductionStatic.getPensionInsurancePercent();
                    } else {
                        salary.setPrivilege(true);
                        for(Privilege privilege : worker.getPrivileges()) {
                            if (privilege.getIdPrivilege()==3)  alreadyHasPrivilege=true;
                            effect+=privilege.getEffect();
                        }
                        if (!alreadyHasPrivilege && lowIncome) {
                            worker.getPrivileges().add(privilegeService.findById(3));
                            effect+=privilegeService.findById(3).getEffect();
                            workerService.update(worker);
                        }
                        if (effect > payment) zeroTax=true;
                        if (zeroTax) tax = payment*DeductionStatic.getPensionInsurancePercent();
                        else tax = (payment-effect)  * DeductionStatic.getTaxPercent() + payment*DeductionStatic.getPensionInsurancePercent();
                    }
                    tax = Float.parseFloat(df.format(tax));
                    salary.setTax(tax);
                    payment = payment-tax;
                    salary.setWorkingDays(workingDays);
                    payment =  Float.parseFloat(df.format(payment));
                    salary.setSum(payment);
                    salary.setWorker(worker);
                }
            }
            salary.setCalculationMonth(DeductionStatic.getMonth());
            salary.setMonthDays(DeductionStatic.getWorkingDays());
           if(salary.getWorker()!=null && (workingDays>0 || vacationDays>0)) {
               if (wasCalculated) salaryService.update(salary);
               else {
                   salaryService.save(salary);
               }
           } else if (wasCalculated && (workingDays==0 && vacationDays==0)) {
               worker.getSalary().remove(salary);
               salaryService.delete(salary.getIdSalary());
           }
        }
    }


    public ModelAndView createCalculatePaymentPage(final int page) {
        ModelAndView mav = new ModelAndView("calculatePayment");
        int recordsPerPage = Integer.valueOf(applicationProperties.getAmountOfLessRecordsPerPage());
        List<Salary> list = salaryService.getAllForPagination((page - 1) * recordsPerPage,
                recordsPerPage);
        int noOfRecords = salaryService.getAll().size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        mav.addObject("noOfPages", noOfPages);
        mav.addObject("currentPage", page);
        mav.addObject("salariesList", list);
        mav.addObject("deduction",new Deduction());
        return mav;
    }

    public ModelAndView calculateWorkersPayment(Deduction deduction) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        DeductionStatic.setMonth(deduction.getMonth());
        DeductionStatic.setWorkingDays(deduction.getWorkingDays());
        calculatePayment();
        return createCalculatePaymentPage(1);
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setPassportService(PassportService passportService) {
        this.passportService = passportService;
    }

    public void setWorkContractService(WorkContractService workContractService) {
        this.workContractService = workContractService;
    }

    public void setWorkerService(WorkerService workerService) {
        this.workerService = workerService;
    }

    public void setApplicationProperties(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    public void setSalaryService(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    public void setWorkingTimeService(WorkingTimeService workingTimeService) {
        this.workingTimeService = workingTimeService;
    }

    public void setPrivilegeService(PrivilegeService privilegeService) {
        this.privilegeService = privilegeService;
    }
}
