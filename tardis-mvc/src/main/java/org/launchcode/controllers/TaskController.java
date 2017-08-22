package org.launchcode.controllers;

import org.launchcode.models.Project;
import org.launchcode.models.Task;
import org.launchcode.models.User;
import org.launchcode.models.data.ProjectDao;
import org.launchcode.models.data.TaskDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("task")
public class TaskController extends AbstractController {

    // Request path: /task
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
        model.addAttribute("tasks", taskDao.findAll());
        model.addAttribute("projects", projectDao.findAll());
        model.addAttribute("description", "Add Description");
        return "task/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddTaskForm(@ModelAttribute  @Valid Task newTask,
                                       Errors errors, @RequestParam int projectId, Model model,
                                       HttpServletRequest request) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Task");
            model.addAttribute("description", "Add Description");
            model.addAttribute("projects", projectDao.findAll());
            return "task/add";
        }

        User owner = getUserFromSession(request.getSession());
        newTask.setOwner(owner);

        Project cat = projectDao.findOne(projectId);
        newTask.setProject(cat);

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

        Project cat = projectDao.findOne(uid);
        List<Task> tasks = cat.getTasks();
        model.addAttribute("tasks", tasks);
        model.addAttribute("title", "Tasks in Project: " + cat.getName());
        return "task/index";
    }

}
