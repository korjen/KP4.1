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
@RequestMapping("/admin")
public class AdminWorkerProfileController {
    @Autowired
    private WorkerOperationsFacade facade;
    @Autowired
    private WorkingTimeFacade timeFacade;

    @RequestMapping(value = "/workers/list", method = RequestMethod.GET)
    public ModelAndView showAllWorker(@RequestParam(required = false) final String page) {
        try {
            Integer.parseInt(page);
        }
        catch (NumberFormatException e) {
            return facade.createWorkerListPage(1);
        }
        return facade.createWorkerListPage(Integer.valueOf(page));
    }

    @RequestMapping(value = {"/workers/profile/list", "/workers/profile/workerUpdate/list",
            "/workers/profile/workerActivate/list", "/workers/profile/workerDeactivate/list",
            "/workers/profile/getWorkerTime/list"},
            method = RequestMethod.GET)
    public ModelAndView returnToWorkersList() {
        return new ModelAndView("redirect:/admin/workers/list");
    }

    @RequestMapping(value = "/workers/profile/{id}", method = RequestMethod.GET)
    public ModelAndView showWorkerProfile(@PathVariable("id") final int id) {
        return facade.createWorkerProfilePage(id);
    }

    @RequestMapping(value = "/workers/newWorker", method = RequestMethod.GET)
    public ModelAndView addWorker() {
        ModelAndView modelAndView = new ModelAndView("newWorkerPage");
        modelAndView.addObject("worker", new Worker() );
        return modelAndView;
    }

    @RequestMapping(value = "/workers/addWorker", method = RequestMethod.POST)
    public ModelAndView addWorker(@ModelAttribute("worker") Worker worker) {
        return facade.addWorker(worker);
    }

    @RequestMapping(value = {"/workers/profile/workerDeactivate/{id}",
            "/users/profile/workerDeactivate/{id}",
            "/workers/profile/workerUpdate/workerDeactivate/{id}",
            "/users/profile/workerUpdate/workerDeactivate/{id}"}, method = RequestMethod.GET)
    public ModelAndView showDeactivationPage(@PathVariable("id") final int id) {
        return facade.showDeactivationPage(id);
    }

    @RequestMapping(value = {"/workers/profile/workerActivate/{id}",
            "/users/profile/workerActivate/{id}",
            "/workers/profile/workerUpdate/workerActivate/{id}",
            "/users/profile/workerUpdate/workerActivate/{id}"}, method = RequestMethod.GET)
    public ModelAndView showActivationPage(@PathVariable("id") final int id) {
        return facade.showActivationPage(id);
    }

    @RequestMapping(value = {"/workers/profile/workerActivate/activate/{id}",
             "/users/profile/workerActivate/activate/{id}," +
                     "/workers/profile/workerUpdate/workerActivate/activate/{id}," +
                     "/users/profile/workerUpdate/workerActivate/activate/{id}"}, method = RequestMethod.POST)
    public ModelAndView activateWorker(@PathVariable("id") final int id, @ModelAttribute("activatedWorker") WorkerDTO worker) {
        return facade.activateWorker(id,worker.getContract().getSignDate(), worker.getContract().getEndDate());
    }

    @RequestMapping(value = {"/workers/profile/workerDeactivate/deactivate/{id}",
            "/users/profile/workerDeactivate/deactivate/{id}, " +
                    "/workers/profile/workerUpdate/workerDeactivate/deactivate/{id}," +
                    "/users/profile/workerUpdate/workerDeactivate/deactivate/{id}"}, method = RequestMethod.POST)
    public ModelAndView deactivateWorker(@PathVariable("id") final int id, @ModelAttribute("deactivatedWorker") WorkerDTO worker) {
        return facade.deactivateWorker(id, worker.getContract().getDismissalDate(), worker.getContract().getOrderNumber());
    }

    @RequestMapping(value = {"/workers/profile/workerDelete/{id}",
            "/users/profile/workerDelete/{id}"}, method = RequestMethod.GET)
    public ModelAndView showDelete(@PathVariable("id") final int id) {
        facade.deleteWorker(id);
        return new ModelAndView("redirect:/admin/workers/list");
    }

    @RequestMapping(value = {"/workers/profile/workerUpdate/{id}",
            "/users/profile/workerUpdate/{id}"}, method = RequestMethod.GET)
    public ModelAndView showEdit(@PathVariable("id") final int id) { return facade.createUpdatePage(id);
    }

    @RequestMapping(value = {"/workers/profile/workerUpdate/workerUpdateProcess",
            "/users/profile/workerUpdate/workerUpdateProcess" }, method = RequestMethod.POST)
    public ModelAndView updateWorker(@ModelAttribute("worker") Worker worker) {
        return facade.updateWorker(worker);
    }

    @RequestMapping(value = {"/workers/profile/getWorkerTime/{id}", "/workers/profile/workerUpdate/getWorkerTime/{id}",
            "/users/profile/getWorkerTime/{id}", "/users/profile/workerUpdate/getWorkerTime/{id}"}, method = RequestMethod.GET)
    public ModelAndView showEditWorkingTimePage(@PathVariable("id") final int id, @RequestParam(required = false) final String page) {
        try {
            Integer.parseInt(page);
        }
        catch (NumberFormatException e) {
            return facade.createEditWorkingTimePage(id,1,"");
        }
        return facade.createEditWorkingTimePage(id, Integer.valueOf(page),"");
    }

    @RequestMapping(value = {"/workers/profile/getWorkerTime/editWorkingTime",
            "/users/profile/getWorkerTime/editWorkingTime"}, method = RequestMethod.POST)
    public ModelAndView editWorkingTime(@ModelAttribute("time") WorkingTime workingTime) {
        return timeFacade.editWorkingTime(workingTime);
    }
}
