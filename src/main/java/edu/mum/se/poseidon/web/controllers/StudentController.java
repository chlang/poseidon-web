package edu.mum.se.poseidon.web.controllers;

import edu.mum.se.poseidon.web.CustomAuthUser;
import edu.mum.se.poseidon.web.mapper.StudentFromDtoMapper;
import edu.mum.se.poseidon.web.mapper.StudentToDtoMapper;
import edu.mum.se.poseidon.web.models.StudentModel;
import edu.mum.se.poseidon.web.services.StudentService;
import edu.mum.se.poseidon.web.services.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class StudentController {

    private StudentService studentService;
    private StudentFromDtoMapper studentFromDtoMapper;
    private StudentToDtoMapper studentToDtoMapper;

    @Autowired
    public StudentController (StudentService studentService, StudentFromDtoMapper studentFromDtoMapper, StudentToDtoMapper studentToDtoMapper) {
        this.studentService = studentService;
        this.studentFromDtoMapper = studentFromDtoMapper;
        this.studentToDtoMapper = studentToDtoMapper;
    }

    @RequestMapping(path = "/student/profile", method = RequestMethod.GET)
    public String getStudentProfile(Model model, Authentication authentication) {
        try {
            CustomAuthUser user = (CustomAuthUser) authentication.getPrincipal();
            StudentDto studentDto = studentService.getStudent(user.getId());
            StudentModel mod = studentFromDtoMapper.getStudentModelFrom(user.getId(), studentDto);
            model.addAttribute("student", mod);
            return "student/profile";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(path = "student/{id}/profile", method = RequestMethod.POST)
    public String updateStudentProfile(@PathVariable long id,
                                       @ModelAttribute("user") @Valid StudentModel studentModel,
                                       Model model) {
        try {
            StudentDto dto = studentToDtoMapper.getStudentDtoFrom(studentModel);
            StudentDto updatedDto = studentService.updateProfile(id, dto);

            studentModel = studentFromDtoMapper.getStudentModelFrom(id, updatedDto);
            model.addAttribute("student", studentModel);
            return "student/profile";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }
    
    @RequestMapping(path = "student/registeration", method = RequestMethod.GET)
    public String registeration(Model model) {
    	
    	return "student/registeration";
    }
}
