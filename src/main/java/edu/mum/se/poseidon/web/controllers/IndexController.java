package edu.mum.se.poseidon.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {


    @RequestMapping(path = "/")
    public String indexStart() {
        return "index";
    }
}
