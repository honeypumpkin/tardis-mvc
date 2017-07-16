package org.launchcode.cheesemvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;


/**
 * Created by robin on 7/16/17.
 */

@Controller
//Request path: /cheese for all things below
@RequestMapping("cheese")
public class CheeseController {
    
    static HashMap<String, String> cheeses = new HashMap<>();
    //by creating this in the top level of the controller, we can access it from any handler within the controller
    //static data only exists in the current memory of the application. It will erase when we restart the app.
    
    
    @RequestMapping(value = "")
    public String index(Model model) {
        //model is what tells Thymeleaf to look for the corresponding view name/value within the view

        
        //use thymeleaf in the template to pass data into the view (.html file)
        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    //handler to display form
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        return "cheese/add";

    }
    
    //handler to add to form by posting the data to the ArrayList cheeses
    @RequestMapping(value="add", method = RequestMethod.POST)
    public String processAddCheeseForm(@RequestParam String cheeseName, @RequestParam String cheeseDescription) { //this is only used in the Spring app
        cheeses.put(cheeseName, cheeseDescription);
        
        //redirect to /cheese, by leaving the redirect empty, it maps to the home path which is /cheese
        return "redirect:";
    }

}
