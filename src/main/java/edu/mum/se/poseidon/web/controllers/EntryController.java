package edu.mum.se.poseidon.web.controllers;

import edu.mum.se.poseidon.web.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EntryController {

    @RequestMapping(path = "/admin/entry", method = RequestMethod.GET)
    public String index() {

        return "admin/entry/index";
    }

    @RequestMapping(path = "/admin/entry/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "admin/user/create";
    }
}
