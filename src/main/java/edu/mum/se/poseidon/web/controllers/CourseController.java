package edu.mum.se.poseidon.web.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.se.poseidon.web.mapper.CourseMapper;
import edu.mum.se.poseidon.web.mapper.FacultyMapper;
import edu.mum.se.poseidon.web.models.Course;
import edu.mum.se.poseidon.web.models.FacultyModel;
import edu.mum.se.poseidon.web.services.CourseService;
import edu.mum.se.poseidon.web.services.FacultyService;
import edu.mum.se.poseidon.web.services.dto.CourseDto;
import edu.mum.se.poseidon.web.services.dto.FacultyDto;
import edu.mum.se.poseidon.web.services.dto.FacultyProfileDto;

/**
 * Created by Munkhtsogt Tsogbadrakh on 10/11/2017.
 *
 * @author Munkhtsogt Tsogbadrakh
 */

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class CourseController {

	public FacultyService facultyService;
	public FacultyMapper facultyMapper;
	public CourseService courseService;
	public CourseMapper courseMapper;
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	public CourseController(FacultyService facultyService, 
			CourseService courseService, FacultyMapper facultyMapper, CourseMapper courseMapper){
		this.facultyService = facultyService;
		this.courseService = courseService;
		this.facultyMapper = facultyMapper;
		this.courseMapper = courseMapper;
	}
	
	@RequestMapping(path = "/admin/course", method = RequestMethod.GET)
	public String index(Model model){
		try {
			List<CourseDto> dtos = courseService.getCourses();
			model.addAttribute("courses", dtos.stream()
					.map(c -> courseMapper.getCourse(c))
					.collect(Collectors.toList()));
			return "admin/course/index";
		}
		catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "error";
		}
		
	}
	
	@RequestMapping(path = "/admin/course/create", method = RequestMethod.GET)
	public String create(Model model) throws Exception {
		try {
			List<FacultyDto> faculties = facultyService.getFacultyList();
			List<CourseDto> courses = courseService.getCourses();
			model.addAttribute("course", new Course());
			model.addAttribute("faculties", faculties.stream()
					.map(f -> facultyMapper.getFaculty(f))
					.collect(Collectors.toList()));
			model.addAttribute("prerequisites", courses.stream()
					.map(c -> courseMapper.getCourse(c))
					.collect(Collectors.toList()));
			return "admin/course/create";
		}
		catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "error";
		}
	}
	
	@RequestMapping(path = "/admin/course/create", method = RequestMethod.POST)
	public String createPOST(@ModelAttribute("course") @Valid Course course,
            BindingResult bindingResult, @Valid Model model){
		
		try {
			if(bindingResult.hasErrors()) {
				List<FacultyDto> faculties = facultyService.getFacultyList();
				List<CourseDto> courses = courseService.getCourses();
				model.addAttribute("prerequisites", courses.stream()
						.map(c -> courseMapper.getCourse(c))
						.collect(Collectors.toList()));
				model.addAttribute("faculties", faculties.stream()
						.map(f -> facultyMapper.getFaculty(f))
						.collect(Collectors.toList()));
				return "admin/course/create";
			}
			courseService.createCourse(courseMapper.getCourseDto(course));
			return "redirect:/admin/course";
		}
		catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
			return "error";
		}
	}
	
	@RequestMapping(path = "/admin/course/{id}/edit")
    public String edit(@PathVariable long id, Model model){
		try {
			List<FacultyDto> faculties = facultyService.getFacultyList();
			List<CourseDto> courses = courseService.getCourses();
			CourseDto dto = courseService.getCourse(id);
			Course course = courseMapper.getCourse(dto);
			model.addAttribute("faculties", faculties.stream()
					.map(f -> facultyMapper.getFaculty(f))
					.collect(Collectors.toList()));
			model.addAttribute("prerequisites", courses.stream()
					.map(c -> courseMapper.getCourse(c))
					.filter(c -> c.getId() != course.getId())
					.collect(Collectors.toList()));
			model.addAttribute("course", course);
			model.addAttribute("selectedPrerequisites", course.getPrerequisites().stream()
					.map(Course::getId).collect(Collectors.toList()));
			model.addAttribute("selectedFaculties", course.getFaculties().stream()
					.map(FacultyModel::getId).collect(Collectors.toList()));
			return "admin/course/edit";
		}
		catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
			return "error";
		}
	}
	
	@RequestMapping(path = "/admin/course/{id}/edit", method = RequestMethod.POST)
    public String editPOST(@ModelAttribute("course") @Valid Course course,
                           BindingResult bindingResult, @Valid Model model) {
		
		try {
			if(bindingResult.hasErrors()) {
				List<FacultyDto> faculties = facultyService.getFacultyList();
				List<CourseDto> courses = courseService.getCourses();
				model.addAttribute("prerequisites", courses.stream()
						.map(c -> courseMapper.getCourse(c))
						.filter(c -> c.getId() != course.getId())
						.collect(Collectors.toList()));
				model.addAttribute("faculties", faculties.stream()
						.map(f -> facultyMapper.getFaculty(f))
						.collect(Collectors.toList()));
				model.addAttribute("selectedPrerequisites", course.getPrerequisites().stream()
						.map(Course::getId).collect(Collectors.toList()));
				model.addAttribute("selectedFaculties", course.getFaculties().stream()
						.map(FacultyModel::getId).collect(Collectors.toList()));
				return "admin/course/edit";
			}
			courseService.editCourse(courseMapper.getCourseDto(course));
			return "redirect:/admin/course";
		}
		catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
			return "error";
		}
	}
		
	@RequestMapping(path = "/admin/course/{id}/delete")
    public String delete(@PathVariable long id, Model model) throws Exception {
		try {
			courseService.deleteCourse(id);
	        return "redirect:/admin/course";
		}
		catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
			return "error";
		}
    }
}
