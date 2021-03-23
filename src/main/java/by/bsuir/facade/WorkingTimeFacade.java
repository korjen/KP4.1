package by.bsuir.facade;

import by.bsuir.ApplicationProperties;
import by.bsuir.dto.WorkingTimeDTO;
import by.bsuir.model.User;
import by.bsuir.model.Worker;
import by.bsuir.model.WorkingTime;
import by.bsuir.service.interfaces.UserService;
import by.bsuir.service.interfaces.WorkerService;
import by.bsuir.service.interfaces.WorkingTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.*;

public class WorkingTimeFacade {
    private UserService userService;
    private WorkingTimeService workingTimeService;
    private WorkerService workerService;
    private ApplicationProperties applicationProperties;
    @Autowired
    private WorkerOperationsFacade workerOperationsFacade;

    public WorkingTime findDate(String datetime, User user){
        String str[] = datetime.split(" ");
        String date = str[0];
        List<WorkingTime> timetable = user.getWorker().getWorkingTime();
        if (timetable.size()>0) {
            for(WorkingTime workingTime : timetable) {
                if (workingTime.getEndTime()!=null) {
                    if (workingTime.getEndTime().contains(date)) {
                        return workingTime;
                    }
                }
                if (workingTime.getStartTime()!=null) {
                    if (workingTime.getStartTime().contains(date)) {
                        return workingTime;
                    }
                }
            }
        }
        return null;
    }

    public ModelAndView setStartTime(final WorkingTimeDTO time) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        User user = userService.findById(currentUser);
        time.setStartTime(time.getStartTime()+":00");
        WorkingTime workingTime = findDate(time.getStartTime(), user);
        if (workingTime != null)  {
            workingTime.setStartTime(time.getStartTime());
            workingTimeService.update(workingTime);
        }
        else {
            workingTime = new WorkingTime();
            workingTime.setStartTime(time.getStartTime());
            workingTime.setAttendance(true);
            workingTime.setWorker(user.getWorker());
            user.getWorker().getWorkingTime().add(workingTime);
            workingTimeService.save(workingTime);
        }
        if (user.getRole().equals("ROLE_ADMIN")) return new ModelAndView("redirect:/admin/workingTime");
        else if (user.getRole().equals("ROLE_MANAGER")) return new ModelAndView("redirect:/manager/workingTime");
        else return new ModelAndView("redirect:/account/workingTime");
    }

    public ModelAndView setEndTime(final WorkingTimeDTO time) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        User user = userService.findById(currentUser);
        time.setEndTime(time.getEndTime()+":00");
        WorkingTime workingTime = findDate(time.getEndTime(), user);
        if (workingTime != null)  {
            workingTime.setEndTime(time.getEndTime());
            workingTimeService.update(workingTime);
        }
        else {
            workingTime = new WorkingTime();
            workingTime.setEndTime(time.getEndTime());
            workingTime.setAttendance(true);
            workingTime.setWorker(user.getWorker());
            workingTimeService.save(workingTime);
        }
        if (user.getRole().equals("ROLE_ADMIN")) return new ModelAndView("redirect:/admin/workingTime");
        else if (user.getRole().equals("ROLE_MANAGER")) return new ModelAndView("redirect:/manager/workingTime");
        else return new ModelAndView("redirect:/account/workingTime");
    }

    public void setStartTimeLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        User user = userService.findById(currentUser);
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String strDate = sdformat.format(now);
        WorkingTime time = findDate(strDate, user);
        if (time==null) {
            WorkingTime workingTime = new WorkingTime();
            workingTime.setWorker(user.getWorker());
            workingTime.setStartTime(strDate);
            workingTime.setEndTime(strDate);
            workingTime.setAttendance(true);
            workingTimeService.save(workingTime);
        }
        else {
            time.setEndTime(strDate);
            workingTimeService.update(time);
        }
    }

    public void setEndTimeLogout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        User user = userService.findById(currentUser);
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String strDate = sdformat.format(now);
        WorkingTime time = findDate(strDate, user);
        if (time!=null) {
            time.setEndTime(strDate);
            time.setAttendance(true);
            workingTimeService.update(time);
        }
        else {
            WorkingTime workingTime = new WorkingTime();
            workingTime.setWorker(user.getWorker());
            workingTime.setEndTime(strDate);
            workingTime.setAttendance(true);
            workingTimeService.save(workingTime);
        }
    }

    @Transactional
    public ModelAndView createTimeListPage(final int page) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        User user = userService.findById(currentUser);
        ModelAndView mav = new ModelAndView("setWorkingTime");
        int recordsPerPage = Integer.valueOf(applicationProperties.getAmountOfMoreRecordsPerPage());

        List<WorkingTime> list = workingTimeService.getAllForPaginationByWorker(user.getWorker().getIdWorker(),(page - 1) * recordsPerPage,
                recordsPerPage);
        int noOfRecords = user.getWorker().getWorkingTime().size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        mav.addObject("startTime", new WorkingTimeDTO());
        mav.addObject("endTime", new WorkingTimeDTO());
        mav.addObject("noOfPages", noOfPages);
        mav.addObject("currentPage", page);
        mav.addObject("timeList", list);
        return mav;
    }

//    @Transactional
    public ModelAndView editWorkingTime(WorkingTime workingTime) {
        Worker worker = workerService.findById(workingTime.getWorker().getIdWorker());
        if (worker.getUser()==null)
            return workerOperationsFacade.createEditWorkingTimePage(worker.getIdWorker(),1,"Сотрудник не зарегистрирован");
        workingTime.setStartTime(workingTime.getStartTime()+":00");
        workingTime.setEndTime(workingTime.getEndTime()+":00");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date startDate = formatter.parse(workingTime.getStartTime());
            Date endDate = formatter.parse(workingTime.getEndTime());
            Calendar start = Calendar.getInstance();
            start.setTime(startDate);
            Calendar end = Calendar.getInstance();
            end.setTime(endDate);
            end.add(Calendar.DATE, 1);
            for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
                WorkingTime temp= findDate(formatter.format(date),worker.getUser());
                if (temp!=null) {
                    temp.setStartTime(formatter.format(date));
                    temp.setEndTime(formatter.format(date));
                    temp.setAttendance(workingTime.isAttendance());
                    temp.setReason(workingTime.getReason());
                    workingTimeService.update(temp);
                }
                else {
                    WorkingTime newTime = new WorkingTime();
                    newTime.setWorker(worker);
                    newTime.setStartTime(formatter.format(date));
                    newTime.setEndTime(formatter.format(date));
                    newTime.setAttendance(workingTime.isAttendance());
                    newTime.setReason(workingTime.getReason());
                    workingTimeService.save(newTime);
                }
            }

        } catch(Exception e) {
        }
        return workerOperationsFacade.createEditWorkingTimePage(worker.getIdWorker(),1,"");
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setWorkingTimeService(WorkingTimeService workingTimeService) {
        this.workingTimeService = workingTimeService;
    }

    public void setApplicationProperties(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    public void setWorkerService(WorkerService workerService) {
        this.workerService = workerService;
    }
}
