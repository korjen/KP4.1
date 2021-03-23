package by.bsuir.controller;

import by.bsuir.dto.WorkerDTO;
import by.bsuir.facade.WorkerOperationsFacade;
import by.bsuir.facade.WorkingTimeFacade;
import by.bsuir.model.Worker;
import by.bsuir.model.WorkingTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/manager/workers")
public class ManagerWorkerProfileController {
    @Autowired
    private WorkerOperationsFacade facade;
    @Autowired
    private WorkingTimeFacade timeFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView showAllWorker(@RequestParam(required = false) final String page) {
        try {
            Integer.parseInt(page);
        }
        catch (NumberFormatException e) {
            return facade.createWorkerListPage(1);
        }
        return facade.createWorkerListPage(Integer.valueOf(page));
    }

    @RequestMapping(value = {"/profile/list", "/profile/workerUpdate/list",
            "/profile/workerActivate/list", "/profile/workerDeactivate/list",
            "/profile/getWorkerTime/list"},
            method = RequestMethod.GET)
    public ModelAndView returnToWorkersList() {
        return new ModelAndView("redirect:/manager/workers/list");
    }

    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
    public ModelAndView showWorkerProfile(@PathVariable("id") final int id) {
        return facade.createWorkerProfilePage(id);
    }

    @RequestMapping(value = "/newWorker", method = RequestMethod.GET)
    public ModelAndView addWorker() {
        ModelAndView modelAndView = new ModelAndView("newWorkerPage");
        modelAndView.addObject("worker", new Worker() );
        return modelAndView;
    }

    @RequestMapping(value = "/addWorker", method = RequestMethod.POST)
    public ModelAndView addWorker(@ModelAttribute("worker") Worker worker) {
        ModelAndView mav = new ModelAndView("newWorkerPage");
        if (facade.checkNewWorker(worker)) {
            return facade.addWorker(worker);
        }
        else {
            mav.addObject("error", "Такой номер договора уже существует");
            return mav;
        }
    }

    @RequestMapping(value = "/profile/workerDeactivate/deactivate/{id}," +
            "/profile/workerUpdate/workerDeactivate/deactivate/{id}", method = RequestMethod.POST)
    public ModelAndView deactivateWorker(@PathVariable("id") final int id, @ModelAttribute("deactivatedWorker") WorkerDTO worker) {
        return facade.deactivateWorker(id, worker.getContract().getDismissalDate(), worker.getContract().getOrderNumber());
    }

    @RequestMapping(value = "/profile/workerDeactivate/{id}," +
            "/profile/workerUpdate/workerDeactivate/{id}", method = RequestMethod.GET)
    public ModelAndView showDeactivationPage(@PathVariable("id") final int id) {
        return facade.showDeactivationPage(id);
    }

    @RequestMapping(value = "/profile/workerActivate/{id}," +
            "/profile/workerUpdate/workerActivate/{id}", method = RequestMethod.GET)
    public ModelAndView showActivationPage(@PathVariable("id") final int id) {
        return facade.showActivationPage(id);
    }

    @RequestMapping(value = "/profile/workerActivate/activate/{id}," +
            "/profile/workerUpdate/workerActivate/activate/{id}", method = RequestMethod.POST)
    public ModelAndView activateWorker(@PathVariable("id") final int id, @ModelAttribute("activatedWorker") WorkerDTO worker) {
        return facade.activateWorker(id,worker.getContract().getSignDate(), worker.getContract().getEndDate());
    }

    @RequestMapping(value = "/profile/workerUpdate/{id}", method = RequestMethod.GET)
    public ModelAndView showEdit(@PathVariable("id") final int id) {
        return facade.createUpdatePage(id);
    }

    @RequestMapping(value = "/profile/workerUpdate/workerUpdateProcess", method = RequestMethod.POST)
    public void updateWorker(@ModelAttribute("worker") Worker worker) {
        facade.updateWorker(worker);
    }

    @RequestMapping(value = {"/profile/getWorkerTime/{id}", "/profile/workerUpdate/getWorkerTime/{id}"}, method = RequestMethod.GET)
    public ModelAndView showEditWorkingTimePage(@PathVariable("id") final int id, @RequestParam(required = false) final String page) {
        try {
            Integer.parseInt(page);
        }
        catch (NumberFormatException e) {
            return facade.createEditWorkingTimePage(id,1,"");
        }
        return facade.createEditWorkingTimePage(id, Integer.valueOf(page),"");
    }

    @RequestMapping(value = "/profile/getWorkerTime/editWorkingTime", method = RequestMethod.POST)
    public ModelAndView deactivateWorker(@ModelAttribute("time") WorkingTime workingTime) {
        return timeFacade.editWorkingTime(workingTime);
    }

}
