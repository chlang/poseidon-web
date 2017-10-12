package edu.mum.se.poseidon.web.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.se.poseidon.web.models.User;

/**
 * Created by Munkhtsogt Tsogbadrakh on 10/11/2017.
 *
 * @author Munkhtsogt Tsogbadrakh
 */

@Controller

public class UserController {

	@RequestMapping(path = "/admin/user", method = RequestMethod.GET)
	public String index() {
		
		return "admin/user/index";
	}
	
	@RequestMapping(path = "/admin/user/create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("user", new User());
		return "admin/user/create";
	}
	
	@RequestMapping(path = "/admin/user/create", method = RequestMethod.POST)
	public String createPOST(@ModelAttribute("user") @Valid User user, 
    		BindingResult bindingResult, @Valid Model model) {
		
		if(bindingResult.hasErrors()) {
			return "admin/user/create";
		}
		
		return "admin/user/index";
	}
}
