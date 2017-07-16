package org.launchcode.hellospring.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.launchcode.hellospring.models.HelloMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by robin on 7/10/17.
 */
@Controller
public class HelloController {

    @RequestMapping(value = "")
    @ResponseBody
    public String index(HttpServletRequest request) {

        String name = request.getParameter("name");

        if (name == null) {
            name = "World";
        }

        return "Hello " + name;

    }

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    @ResponseBody
    public String helloForm() {

        String html = "<form method='post'>" +
                "<input type='text' name='name' />" +
                "<select name='language'>" +
                    "<option value='french'>French</option>" +
                    "<option value='spanish'>Spanish</option>" +
                    "<option value='german'>German</option>" +
                    "<option value='italian'>Italian</option>" +
                    "<option value='hindi'>Hindi</option>" +
                "</select>" +
                "<input type='submit' value='Greet Me!'/>"+
                "</form>";

        return html;

    }



    @RequestMapping(value = "hello", method= RequestMethod.POST)
    @ResponseBody
    public String helloPost(@RequestParam String name, @RequestParam String language) {

        return HelloMessage.createMessage(name, language);

    }

    @RequestMapping(value = "hello/{name}")
    @ResponseBody
    public String helloUrlSegment(@PathVariable String name){

        return "Hello " + name;
    }


    @RequestMapping(value = "goodbye")
    public String goodbye() {
        return "redirect:/";

    }
}
