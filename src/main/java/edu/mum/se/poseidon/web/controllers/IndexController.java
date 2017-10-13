package edu.mum.se.poseidon.web.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {


    @RequestMapping(path = "/")
    public void indexStart(HttpServletResponse httpServletResponse) throws IOException {
    	httpServletResponse.sendRedirect("/admin");
    }
}
