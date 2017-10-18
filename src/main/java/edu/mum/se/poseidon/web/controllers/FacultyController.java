package edu.mum.se.poseidon.web.controllers;

import edu.mum.se.poseidon.web.mapper.FacultyFromDtoMapper;
import edu.mum.se.poseidon.web.mapper.FacultyToDtoMapper;
import edu.mum.se.poseidon.web.models.FacultyModel;
import edu.mum.se.poseidon.web.services.FacultyService;
import edu.mum.se.poseidon.web.services.dto.FacultyProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class FacultyController {

    private FacultyService facultyService;
    private FacultyFromDtoMapper facultyFromDtoMapper;
    private FacultyToDtoMapper facultyToDtoMapper;

    @Autowired
    public FacultyController(FacultyService facultyService,
                             FacultyFromDtoMapper facultyFromDtoMapper,
                             FacultyToDtoMapper facultyToDtoMapper) {
        this.facultyService = facultyService;
        this.facultyFromDtoMapper = facultyFromDtoMapper;
        this.facultyToDtoMapper = facultyToDtoMapper;
    }


    @RequestMapping(path = "/faculties/{id}/profile", method = RequestMethod.GET)
    public String getStudentProfile(@PathVariable long id, Model model) {
        try {
            FacultyProfileDto facultyDto = facultyService.getFaculty(id);
            FacultyModel mod = facultyFromDtoMapper.getFacultyModelFrom(id, facultyDto);
            model.addAttribute("faculty", mod);
            return "faculty/profile";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(path = "faculties/{id}/profile", method = RequestMethod.POST)
    public String updateStudentProfile(@PathVariable long id,
                                       @ModelAttribute("user") @Valid FacultyModel facultyModel,
                                       Model model) {
        try {
            FacultyProfileDto dto = facultyToDtoMapper.getFacultyDtoFrom(facultyModel);
            FacultyProfileDto updatedDto = facultyService.updateProfile(id, dto);
            model.addAttribute("faculty", updatedDto);
            return "faculty/profile";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }
}
