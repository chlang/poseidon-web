package edu.mum.se.poseidon.web.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import edu.mum.se.poseidon.web.models.User;

/**
 * Created by Munkhtsogt Tsogbadrakh on 10/11/2017.
 *
 * @author Munkhtsogt Tsogbadrakh
 */

@Controller

public class UserController {

	RestTemplate restTemplate = new RestTemplate();
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
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
		
		if(user.getType().equals("student")) {
			HttpEntity<User> entity = new HttpEntity<User>(user);
		    restTemplate.exchange("http://localhost:8085/student/create", 
		    		HttpMethod.PUT, entity, User.class);
		}
		
		return "admin/user/index";
	}
}
