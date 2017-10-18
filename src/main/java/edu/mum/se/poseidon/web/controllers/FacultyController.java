package edu.mum.se.poseidon.web.controllers;

import edu.mum.se.poseidon.web.CustomAuthUser;
import edu.mum.se.poseidon.web.mapper.FacultyFromDtoMapper;
import edu.mum.se.poseidon.web.mapper.FacultyToDtoMapper;
import edu.mum.se.poseidon.web.models.FacultyModel;
import edu.mum.se.poseidon.web.services.FacultyService;
import edu.mum.se.poseidon.web.services.dto.FacultyDto;
import edu.mum.se.poseidon.web.services.dto.FacultyProfileDto;
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


    @RequestMapping(path = "/faculty/profile", method = RequestMethod.GET)
    public String getStudentProfile(Model model, Authentication authentication) {
        try {
            CustomAuthUser user = (CustomAuthUser) authentication.getPrincipal();
            FacultyProfileDto facultyDto = facultyService.getFaculty(user.getId());
            FacultyModel mod = facultyFromDtoMapper.getFacultyModelFrom(user.getId(), facultyDto);
            model.addAttribute("faculty", mod);
            return "faculty/profile";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(path = "faculty/{id}/profile", method = RequestMethod.POST)
    public String updateStudentProfile(@PathVariable long id,
                                       @ModelAttribute("user") @Valid FacultyModel facultyModel,
                                       Model model) {
        try {
            FacultyProfileDto dto = facultyToDtoMapper.getFacultyDtoFrom(facultyModel);
            FacultyProfileDto updatedDto = facultyService.updateProfile(id, dto);

            facultyModel = facultyFromDtoMapper.getFacultyModelFrom(id, updatedDto);
            model.addAttribute("faculty", facultyModel);
            return "faculty/profile";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }
}
