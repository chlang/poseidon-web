package edu.mum.se.poseidon.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccountController {
	
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String index() {
		return "login";
	}
}
