package org.launchcode.controllers;

import org.launchcode.models.Project;
import org.launchcode.models.Task;
import org.launchcode.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Robin
 */
@Controller
@RequestMapping("task")
public class TaskController extends HomeController {

    // Request path: /tawsk
    @RequestMapping(value = "")
    public String index(Model model, HttpServletRequest request) {

        User user = getUserFromSession(request.getSession());

        model.addAttribute("tasks", taskDao.findByOwner(user));
        model.addAttribute("title", "My Tasks");

        return "task/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddTaskForm(Model model) {
        model.addAttribute("title", "Add Task");
        model.addAttribute(new Task());
        model.addAttribute("projects", projectDao.findAll());
        return "task/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddTaskForm(@ModelAttribute  @Valid Task newTask,
                                       Errors errors, @RequestParam int projectID, Model model,
                                       HttpServletRequest request) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Task");
            model.addAttribute("projects", projectDao.findAll());
            return "task/add";
        }

        User owner = getUserFromSession(request.getSession());
        newTask.setOwner(owner);

        Project proj = projectDao.findOne(projectID);
        newTask.setProject(proj);

        taskDao.save(newTask);

        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveTaskForm(Model model, HttpServletRequest request) {

        User user = getUserFromSession(request.getSession());

        model.addAttribute("tasks", taskDao.findByOwner(user));
        model.addAttribute("title", "Remove Task");
        return "task/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveTaskForm(@RequestParam int[] ids) {

        for (int id : ids) {
            taskDao.delete(id);
        }

        return "redirect:";
    }

    @RequestMapping(value = "project", method = RequestMethod.GET)
    public String project(Model model, @RequestParam int uid) {

        Project proj = projectDao.findOne(uid);
        List<Task> tasks = proj.getTasks();
        model.addAttribute("tasks", tasks);
        model.addAttribute("title", "Tasks in Project: " + proj.getName());
        return "task/index";
    }

}
