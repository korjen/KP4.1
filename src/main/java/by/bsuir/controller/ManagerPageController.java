package by.bsuir.controller;

import by.bsuir.dto.UserDTO;
import by.bsuir.dto.WorkingTimeDTO;
import by.bsuir.facade.StartApplicationFacade;
import by.bsuir.facade.UserOperationsFacade;
import by.bsuir.facade.WorkerOperationsFacade;
import by.bsuir.facade.WorkingTimeFacade;
import by.bsuir.utility.Deduction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/manager")
public class ManagerPageController {
    @Autowired
    private UserOperationsFacade userOperationsFacade;
    @Autowired
    private WorkingTimeFacade workingTimeFacade;
    @Autowired
    private StartApplicationFacade startAppFacade;
    @Autowired
    private WorkerOperationsFacade workerOperationsFacade;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView startPage() {
        workingTimeFacade.setStartTimeLogin();
        ModelAndView mav = new ModelAndView("managerPage");
        String greeting = startAppFacade.getGreeting();
        String warning = startAppFacade.contractExpirationDate();
        mav.addObject("greeting", greeting);
        mav.addObject("warning", warning);
        return mav;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView homePage() {
        ModelAndView mav = new ModelAndView("managerPage");
        String greeting = startAppFacade.getGreeting();
        String warning = startAppFacade.contractExpirationDate();
        mav.addObject("greeting", greeting);
        mav.addObject("warning", warning);
        return mav;
    }

    @RequestMapping(value = "/userProfile", method = RequestMethod.GET)
    public ModelAndView currentUserProfile() {
        return userOperationsFacade.createCurrentUserPage();
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public ModelAndView currentUserChangePassword(@ModelAttribute("userDTO") UserDTO userDTO) {
        return userOperationsFacade.changePassword(userDTO);
    }

    @RequestMapping(value = "/setWorkingTime/home", method = RequestMethod.GET)
    public ModelAndView returnToHomePage() {
        return new ModelAndView("managerPage");
    }

    @RequestMapping(value = "/workingTime", method = RequestMethod.GET)
    public ModelAndView setWorkingTime(@RequestParam(required = false) final String page) {
        try {
            Integer.parseInt(page);
        }
        catch (NumberFormatException e) {
            return workingTimeFacade.createTimeListPage(1);
        }
        return workingTimeFacade.createTimeListPage(Integer.valueOf(page));
    }

    @RequestMapping(value = "/paymentHistory/workerPrivileges", method = RequestMethod.GET)
    public ModelAndView getWorkerPrivileges() {
        return workerOperationsFacade.createPrivilegesPage();
    }

    @RequestMapping(value = "/paymentHistory/workerPrivileges/back", method = RequestMethod.GET)
    public ModelAndView backFromWorkerPrivileges() {
        return new ModelAndView("redirect:/manager/paymentHistory");
    }

    @RequestMapping(value = "/setStartTime", method = RequestMethod.POST)
    public ModelAndView setStartTime(@ModelAttribute("startTime") WorkingTimeDTO time) {
        return workingTimeFacade.setStartTime(time);
    }

    @RequestMapping(value = "/setEndTime", method = RequestMethod.POST)
    public ModelAndView setEndTime(@ModelAttribute("endTime") WorkingTimeDTO time) {
        return workingTimeFacade.setEndTime(time);
    }

    @RequestMapping(value = "/paymentHistory", method = RequestMethod.GET)
    public ModelAndView getPaymentHistory(@RequestParam(required = false) final String page) {
        try {
            Integer.parseInt(page);
        }
        catch (NumberFormatException e) {
            return workerOperationsFacade.createPaymentHistoryPage(1);
        }
        return workerOperationsFacade.createPaymentHistoryPage(Integer.valueOf(page));
    }

    @RequestMapping(value = "/workersPaymentList", method = RequestMethod.GET)
    public ModelAndView getWorkersPaymentList(@RequestParam(required = false) final String page) {
        try {
            Integer.parseInt(page);
        }
        catch (NumberFormatException e) {
            return workerOperationsFacade.createCalculatePaymentPage(1);
        }
        return workerOperationsFacade.createCalculatePaymentPage(Integer.valueOf(page));
    }

    @RequestMapping(value = {"/calculate","/calculatePayment/calculate"}, method = RequestMethod.POST)
    public ModelAndView calculatePayment(@ModelAttribute("deduction") Deduction deduction) {
        return workerOperationsFacade.calculateWorkersPayment(deduction);
    }

    @RequestMapping(value = "/workersPaymentList/profile/{id}", method = RequestMethod.GET)
    public ModelAndView showWorkerPaymentProfile(@PathVariable("id") final int id) {
        return workerOperationsFacade.createWorkerPaymentProfilePage(id);
    }
}
