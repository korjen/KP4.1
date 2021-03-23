package by.bsuir.facade;

import by.bsuir.model.User;
import by.bsuir.model.WorkContract;
import by.bsuir.model.Worker;
import by.bsuir.service.interfaces.UserService;
import by.bsuir.service.interfaces.WorkContractService;
import by.bsuir.service.interfaces.WorkerService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class StartApplicationFacade {
    private UserService userService;
    private WorkerService workerService;
    private WorkContractService workContractService;

    public String getGreeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        User user = userService.findById(currentUser);
        GregorianCalendar time = new GregorianCalendar();
        int hour = time.get(Calendar.HOUR_OF_DAY);
        String greeting;
        if (hour >= 4 && hour < 12){
            greeting = "Доброе утро, ";
        }
        else if (hour < 16) {
            greeting = "Добрый день, ";
        }
        else {
            greeting = "Добрый вечер, ";
        }
        greeting += user.getWorker().getPassport().getSurname() + " " + user.getWorker().getPassport().getName() + " " + user.getWorker().getPassport().getPatronymic();
        return greeting;
    }

    public String contractExpirationDate() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        User user = userService.findById(currentUser);
        String warning="";
        GregorianCalendar time = new GregorianCalendar();
        int month = time.get(Calendar.MONTH) + 1;
        int year = time.get(Calendar.YEAR);
        String date = user.getWorker().getContract().getEndDate();
        String[] str = date.split("-");
        int contractYear = Integer.parseInt(str[0]);
        int contractMonth = Integer.parseInt(str[1]);
        if (month==contractMonth && year==contractYear)
            warning = "Ваш трудовой договор закачивается в этом месяце! ("+str[2]+"/"+str[1]+"/"+str[0]+")";
        return warning;
    }

    public void checkContractsDate() {
        List<Worker> workers = workerService.getAll();
        for (Worker worker : workers) {
            if(isContractExpired(worker.getContract().getEndDate())) {
                User user = worker.getUser();
                worker.setActive(false);
                worker.getContract().setDismissalDate(worker.getContract().getEndDate());
                if (user!=null) {
                    worker.setUser(null);
                    userService.delete(user.getLogin());
                }
                workerService.update(worker);
            } else {
                worker.setActive(true);
                workerService.update(worker);
            }
        }
    }

    public boolean isContractExpired(String contractEndDate) {
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        Date contractDate= new Date();
        try {
            sdformat.format(now);
            contractDate = sdformat.parse(contractEndDate);
            if(contractDate.compareTo(now) < 0){  //|| contractDate.compareTo(now) == 0) {
                return true;
            } else return false;

        } catch (Exception e) {}
        return false;
    }

    public int validateRegistration(User user){
        WorkContract contract = workContractService.findById(user.getWorker().getContract().getContractNumber());
        User temp = userService.findById(user.getLogin());
        if (contract!=null) {
            if (!isContractExpired(contract.getEndDate())) {
                if (temp == null) {
                    user.setRole("ROLE_WORKER");
                    user.setWorker(contract.getWorker());
                    userService.save(user);
                    return 0;
                } else return 1;
            } else return -2;
        } else return -1;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setWorkerService(WorkerService workerService) {
        this.workerService = workerService;
    }

    public void setWorkContractService(WorkContractService workContractService) {
        this.workContractService = workContractService;
    }
}
