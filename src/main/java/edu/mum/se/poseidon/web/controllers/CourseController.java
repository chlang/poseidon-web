package edu.mum.se.poseidon.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.se.poseidon.web.models.Course;

/**
 * Created by Munkhtsogt Tsogbadrakh on 10/11/2017.
 *
 * @author Munkhtsogt Tsogbadrakh
 */

@Controller
public class CourseController {

	@RequestMapping(path = "/admin/course", method = RequestMethod.GET)
	public String index(Model model) {
		
		return "admin/course/index";
	}
	
	@RequestMapping(path = "/admin/course/create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("coure", new Course());
		return "admin/course/create";
	}
}
