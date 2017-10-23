package edu.mum.se.poseidon.web.controllers;

import edu.mum.se.poseidon.web.CustomAuthUser;
import edu.mum.se.poseidon.web.mapper.RegistrationMapper;
import edu.mum.se.poseidon.web.models.registration.RegistrationModel;
import edu.mum.se.poseidon.web.services.StudentRegistrationService;
import edu.mum.se.poseidon.web.services.dto.SectionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@PreAuthorize("hasRole('ROLE_STUDENT')")
public class StudentRegistrationController {

    private StudentRegistrationService studentRegistrationService;
    private RegistrationMapper registrationMapper;
    private static final Logger logger = LoggerFactory.getLogger(StudentRegistrationController.class);

    @Autowired
    public StudentRegistrationController(StudentRegistrationService studentRegistrationService,
                                         RegistrationMapper registrationMapper) {
        this.studentRegistrationService = studentRegistrationService;
        this.registrationMapper = registrationMapper;
    }

    @RequestMapping(path = "/student/registration", method = RequestMethod.GET)
    public String index(Model model, Authentication authentication) throws Exception {
        try {
            CustomAuthUser user = (CustomAuthUser) authentication.getPrincipal();
            long studentId = user.getId();
            List<SectionDto> sectionDtoList = studentRegistrationService.getAvailableSections(studentId);
            List<RegistrationModel> registrationModelList = registrationMapper.getRegistrationModelFrom(sectionDtoList);
            model.addAttribute("registrationModels", registrationModelList);
            return "student/registration";
        } catch (Exception e) {
            if (e.getMessage().contains("404")) {
                model.addAttribute("errorMessage", "Section is not found.");
            } else {
                model.addAttribute("errorMessage", e.getMessage());
            }
            return "error";
        }
    }

    @RequestMapping(path = "/student/registration", method = RequestMethod.POST)
    public String registration(@RequestParam(value = "sectionIds", required = false) List<String> sectionIds,
                               Model model, Authentication authentication) {
        try {
            CustomAuthUser user = (CustomAuthUser) authentication.getPrincipal();
            long studentId = user.getId();
            if (sectionIds != null) {
                for (String sectionId : sectionIds) {
                    studentRegistrationService.registerToSection(studentId, Long.valueOf(sectionId));
                }
            }
            return "redirect:/student/registration";
        } catch (Exception e) {
            if (e.getMessage().contains("400")) {
                model.addAttribute("errorMessage", "Seat is not available or you don't have prequisites.");
            } else if (e.getMessage().contains("404")) {
                model.addAttribute("errorMessage", "Section or student is not found.");
            } else {
                model.addAttribute("errorMessage", e.getMessage());
            }
            return "error";
        }
    }

}
