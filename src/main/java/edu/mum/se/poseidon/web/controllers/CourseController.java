package edu.mum.se.poseidon.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.se.poseidon.web.models.Course;
import edu.mum.se.poseidon.web.services.FacultyService;
import edu.mum.se.poseidon.web.services.dto.CourseDto;
import edu.mum.se.poseidon.web.services.dto.FacultyDto;
import edu.mum.se.poseidon.web.services.dto.UserDto;

/**
 * Created by Munkhtsogt Tsogbadrakh on 10/11/2017.
 *
 * @author Munkhtsogt Tsogbadrakh
 */

@Controller
public class CourseController {

	public FacultyService facultyService;
	
	@Autowired
	public CourseController(FacultyService facultyService){
		this.facultyService = facultyService;
	}
	
	@RequestMapping(path = "/admin/course", method = RequestMethod.GET)
	public String index(Model model) {
		
		return "admin/course/index";
	}
	
	@RequestMapping(path = "/admin/course/create", method = RequestMethod.GET)
	public String create(Model model) throws Exception {
		List<FacultyDto> professors = facultyService.getFaculties();
		List<CourseDto> prerequisites = new ArrayList<>();
		model.addAttribute("course", new Course());
		model.addAttribute("professors", professors);
		model.addAttribute("prerequisites", prerequisites);
		return "admin/course/create";
	}
}
