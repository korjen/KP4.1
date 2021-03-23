package by.bsuir.controller;

import by.bsuir.dto.UserDTO;
import by.bsuir.dto.WorkerDTO;
import by.bsuir.facade.UserOperationsFacade;
import by.bsuir.facade.WorkerOperationsFacade;
import by.bsuir.facade.WorkingTimeFacade;
import by.bsuir.model.User;
import by.bsuir.model.Worker;
import by.bsuir.model.WorkingTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/users")
public class AdminUserProfileController {
    @Autowired
    private UserOperationsFacade userFacade;
    @Autowired
    private WorkerOperationsFacade workerFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView showAllUser(@RequestParam(required = false) final String page) {
        try {
            Integer.parseInt(page);
        }
        catch (NumberFormatException e) {
            return userFacade.createUserListPage(1);
        }
        return userFacade.createUserListPage(Integer.valueOf(page));
    }

    @RequestMapping(value = {"/profile/list", "/profile/workerUpdate/list", "/profile/workerActivate/list",
            "/profile/workerDeactivate/list", "/profile/getWorkerTime/list"},
            method = RequestMethod.GET)
    public ModelAndView returnToUsersList() {
        return new ModelAndView("redirect:/admin/users/list");
    }

    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
    public ModelAndView showUserProfile(@PathVariable("id") final String id) {
        return workerFacade.createWorkerProfilePage(id);
    }

    @RequestMapping(value = "/editUser/{id}", method = RequestMethod.GET)
    public ModelAndView showUserUpdate(@PathVariable("id") final String id) {
        return userFacade.createUserUpdatePage(id);
    }

    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable("id") final String id) {
        return userFacade.delete(id);
    }


    @RequestMapping(value = "/editUser/update/{id}", method = RequestMethod.POST)
    public ModelAndView updateUser(@ModelAttribute("user") UserDTO user,@PathVariable("id") final String id) {
        return userFacade.updateUser(id, user);
    }

    @RequestMapping(value = "/newUser", method = RequestMethod.GET)
    public ModelAndView showNewUserPage() {
        ModelAndView modelAndView = new ModelAndView("newUserPage");
        modelAndView.addObject("user", new User() );
        return modelAndView;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("user") User user) {
        ModelAndView mav = new ModelAndView("newUserPage");
        int result = workerFacade.checkNewUser(user);
        if (result==0) {
            return workerFacade.addUser(user);
        }
        else if (result == 1){
            mav.addObject("error", "Пользователь с таким логином уже существует");
            return mav;
        }
        else if (result == -1){
            mav.addObject("error", "Договора с таким номером не существует");
            return mav;
        }
        else if (result == -2){
            mav.addObject("error", "Сотрудник с таким номером договора уже зарегистрирован");
            return mav;
        }
        else if (result == -3){
            mav.addObject("error", "Трудовой договор этого сотрудника истек");
            return mav;
        }
        return mav;
    }

}
