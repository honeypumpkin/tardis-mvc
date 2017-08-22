package org.launchcode.controllers;

import org.launchcode.models.Project;
import org.launchcode.models.data.ProjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("project")
public class ProjectController extends AbstractController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("title", "Projects");
        model.addAttribute("projects", projectDao.findAll());
        return "project/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new Project());
        model.addAttribute("title", "Add Project");
        return "project/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Project project, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Project");
            return "project/add";
        }

        projectDao.save(project);
        return "redirect:";
    }

}
