package org.launchcode.controllers;

import org.launchcode.models.Task;
import org.launchcode.models.Owner;
import org.launchcode.models.data.TaskDao;
import org.launchcode.models.data.OwnerDao;
import org.launchcode.models.forms.AddOwnerItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "owner")
public class OwnerController extends AbstractController {

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Assigned to");
        model.addAttribute("owners", ownerDao.findAll());
        return "owner/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add Owner");
        model.addAttribute(new Owner());
        return "owner/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Owner owner,
                      Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Owner");
            return "owner/add";
        }

        ownerDao.save(owner);

        return "redirect:view/" + owner.getUid();
    }

    @RequestMapping(value = "view/{ownerId}", method = RequestMethod.GET)
    public String viewOwner(Model model, @PathVariable int ownerId) {

        Owner owner = ownerDao.findOne(ownerId);
        model.addAttribute("owner", owner);

        return "owner/view";
    }

    @RequestMapping(value = "add-item/{ownerId}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable int ownerId) {

        Owner owner = ownerDao.findOne(ownerId);

        AddOwnerItemForm form = new AddOwnerItemForm(
                taskDao.findAll(),
                owner);

        model.addAttribute("title", "Add owner to task: " + owner.getName());
        model.addAttribute("form", form);
        return "owner/add-item";
    }

    @RequestMapping(value = "add-item", method = RequestMethod.POST)
    public String addItem(Model model,
                          @ModelAttribute @Valid AddOwnerItemForm form, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "owner/add-item";
        }

        Task theTask = taskDao.findOne(form.getTaskId());
        Owner theOwner = ownerDao.findOne(form.getOwnerId());
        theOwner.addItem(theTask);
        ownerDao.save(theOwner);

        return "redirect:/owner/view/" + theOwner.getUid();
    }

}
