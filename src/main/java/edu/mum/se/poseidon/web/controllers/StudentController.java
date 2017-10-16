package edu.mum.se.poseidon.web.controllers;

import edu.mum.se.poseidon.web.mapper.StudentFromDtoMapper;
import edu.mum.se.poseidon.web.mapper.StudentToDtoMapper;
import edu.mum.se.poseidon.web.models.StudentModel;
import edu.mum.se.poseidon.web.services.StudentService;
import edu.mum.se.poseidon.web.services.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(path = "/students/{id}/profile", method = RequestMethod.GET)
    public String getStudentProfile(@PathVariable long id, Model model) {
        try {
            StudentDto studentDto = studentService.getStudent(id);
            StudentModel mod = studentFromDtoMapper.getStudentModelFrom(studentDto);
            model.addAttribute("student", mod);
            return "student/profile";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(path = "students/{id}/profile", method = RequestMethod.POST)
    public String updateStudentProfile(@PathVariable long id,
                                       @ModelAttribute("user") @Valid StudentModel studentModel,
                                       Model model) {
        try {
            StudentDto dto = studentToDtoMapper.getStudentDtoFrom(studentModel);
            StudentDto updatedDto = studentService.updateProfile(dto);
            model.addAttribute("student", updatedDto);
            return "student/profile";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }
}
