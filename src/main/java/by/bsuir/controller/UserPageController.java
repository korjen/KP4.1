package by.bsuir.controller;

import by.bsuir.dto.UserDTO;
import by.bsuir.dto.WorkingTimeDTO;
import by.bsuir.facade.StartApplicationFacade;
import by.bsuir.facade.UserOperationsFacade;
import by.bsuir.facade.WorkerOperationsFacade;
import by.bsuir.facade.WorkingTimeFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/account")
public class UserPageController {
    @Autowired
    private UserOperationsFacade userFacade;
    @Autowired
    private WorkerOperationsFacade workerFacade;
    @Autowired
    private WorkingTimeFacade workingTimeFacade;
    @Autowired
    private StartApplicationFacade startAppFacade;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView startPage() {
        workingTimeFacade.setStartTimeLogin();
        ModelAndView mav = new ModelAndView("workerPage");
        String greeting = startAppFacade.getGreeting();
        String warning = startAppFacade.contractExpirationDate();
        mav.addObject("greeting", greeting);
        mav.addObject("warning", warning);
        return mav;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView homePage() {
        ModelAndView mav = new ModelAndView("workerPage");
        String greeting = startAppFacade.getGreeting();
        String warning = startAppFacade.contractExpirationDate();
        mav.addObject("greeting", greeting);
        mav.addObject("warning", warning);
        return mav;
    }

    @RequestMapping(value = "/userProfile", method = RequestMethod.GET)
    public ModelAndView currentUserProfile() {
        return userFacade.createCurrentUserPage();
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public ModelAndView currentUserChangePassword(@ModelAttribute("userDTO") UserDTO userDTO) {
        return userFacade.changePassword(userDTO);
    }

    @RequestMapping(value = "/setWorkingTime/home", method = RequestMethod.GET)
    public ModelAndView returnToHomePage() {
        return new ModelAndView("workerPage");
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

    @RequestMapping(value = "/paymentHistory", method = RequestMethod.GET)
    public ModelAndView getPaymentHistory(@RequestParam(required = false) final String page) {
        try {
            Integer.parseInt(page);
        }
        catch (NumberFormatException e) {
            return workerFacade.createPaymentHistoryPage(1);
        }
        return workerFacade.createPaymentHistoryPage(Integer.valueOf(page));
    }

    @RequestMapping(value = "/paymentHistory/workerPrivileges", method = RequestMethod.GET)
    public ModelAndView getWorkerPrivileges() {
        return workerFacade.createPrivilegesPage();
    }

    @RequestMapping(value = "/paymentHistory/workerPrivileges/back", method = RequestMethod.GET)
    public ModelAndView backFromWorkerPrivileges() {
        return new ModelAndView("redirect:/account/paymentHistory");
    }

    @RequestMapping(value = "/setStartTime", method = RequestMethod.POST)
    public ModelAndView setStartTime(@ModelAttribute("startTime") WorkingTimeDTO time) {
        return workingTimeFacade.setStartTime(time);
    }

    @RequestMapping(value = "/setEndTime", method = RequestMethod.POST)
    public ModelAndView setEndTime(@ModelAttribute("endTime") WorkingTimeDTO time) {
        return workingTimeFacade.setEndTime(time);
    }
}
