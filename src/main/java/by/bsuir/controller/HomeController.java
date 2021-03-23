package by.bsuir.controller;

import by.bsuir.facade.StartApplicationFacade;
import by.bsuir.facade.UserOperationsFacade;
import by.bsuir.facade.WorkerOperationsFacade;
import by.bsuir.facade.WorkingTimeFacade;
import by.bsuir.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class HomeController {
    @Autowired
    private StartApplicationFacade startAppFacade;
    @Autowired
    private WorkingTimeFacade workingTimeFacade;
    @Autowired
    private WorkerOperationsFacade workerOperationsFacade;
    @Autowired
    private UserOperationsFacade userOperationsFacade;

    @RequestMapping(value = "/backToCurrentUser", method = RequestMethod.GET)
    public ModelAndView backToCurrentUser() {
        int result = userOperationsFacade.isAdmin();
        if(result == 1) return new ModelAndView("redirect:/admin/home");
        else if (result==0) return new ModelAndView("redirect:/manager/home");
        else if (result==-2) return new ModelAndView("redirect:/");
        else return new ModelAndView("redirect:/account/home");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showRegister(@RequestParam(value = "error", required = false) final String error) {
        ModelAndView mav = new ModelAndView("login");
        if (error != null) {
            mav.addObject("error", "Неверный логин или пароль");
        }
        return mav;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView registerUser() {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("user", new User() );
        return modelAndView;
    }

    @RequestMapping(value = "/checkRegister", method = RequestMethod.POST)
    public ModelAndView checkRegister(@ModelAttribute("user") User user) throws IOException {
        int result = startAppFacade.validateRegistration(user);
        ModelAndView mav = new ModelAndView("register");
        if(result==0) {
            return new ModelAndView("redirect:/login");
        }
        else if (result==1){
            mav.addObject("error", "Такой пользователь уже существует");
            return mav;
        }
        else if (result==-1){
            mav.addObject("error", "Сотрудника с таким номером договора не существует");
            return mav;
        }
        else if (result==-2){
            mav.addObject("error", "Срок Вашего трудового договора истек");
            return mav;
        }
        return mav;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView homePage() {
        startAppFacade.checkContractsDate();
        workerOperationsFacade.setPrivileges();
        return new ModelAndView("home");
    }

    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    public ModelAndView logout()  {
        workingTimeFacade.setEndTimeLogout();
        return new ModelAndView("redirect:/logout");
    }
}


