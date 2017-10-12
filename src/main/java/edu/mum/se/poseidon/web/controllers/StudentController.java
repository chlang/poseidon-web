package edu.mum.se.poseidon.web.controllers;

import edu.mum.se.poseidon.web.mapper.StudentFromDtoMapper;
import edu.mum.se.poseidon.web.models.StudentModel;
import edu.mum.se.poseidon.web.services.StudentService;
import edu.mum.se.poseidon.web.services.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {

    private StudentService studentService;
    private StudentFromDtoMapper studentFromDtoMapper;

    @Autowired
    public StudentController (StudentService studentService, StudentFromDtoMapper studentFromDtoMapper) {
        this.studentService = studentService;
        this.studentFromDtoMapper = studentFromDtoMapper;
    }

    @RequestMapping(path = "/students/{id}")
    public String getStudentProfile(@PathVariable long id, Model model) {
        try {
            StudentDto studentDto = studentService.getStudent(id);
            StudentModel mod = studentFromDtoMapper.getStudentModelFrom(studentDto);
            model.addAttribute(mod);
            return "student/profile";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

}
