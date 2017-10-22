package edu.mum.se.poseidon.web.controllers;

import edu.mum.se.poseidon.web.CustomAuthUser;
import edu.mum.se.poseidon.web.mapper.RegistrationMapper;
import edu.mum.se.poseidon.web.models.registration.RegistrationModel;
import edu.mum.se.poseidon.web.services.StudentRegistrationService;
import edu.mum.se.poseidon.web.services.dto.SectionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class StudentRegistrationController {

    private StudentRegistrationService studentRegistrationService;
    private RegistrationMapper registrationMapper;
    private static final Logger logger = LoggerFactory.getLogger(SectionController.class);

    @Autowired
    public StudentRegistrationController(StudentRegistrationService studentRegistrationService,
                                         RegistrationMapper registrationMapper) {
        this.studentRegistrationService = studentRegistrationService;
        this.registrationMapper = registrationMapper;
    }

    @RequestMapping(path = "/student/registration", method = RequestMethod.GET)
    public String index(Model model, Authentication authentication) throws Exception {
    	CustomAuthUser user = (CustomAuthUser) authentication.getPrincipal();
    	long studentId = user.getId();
        List<SectionDto> sectionDtoList = studentRegistrationService.getAvailableSections(studentId);
        List<RegistrationModel> registrationModelList = registrationMapper.getRegistrationModelFrom(sectionDtoList);
        model.addAttribute("registrationModels", registrationModelList);
        return "student/registration";
    }

    @RequestMapping(path = "/student/registration", method = RequestMethod.POST)
    public String registrationPOST(@RequestParam("sectionIds") List<String> sectionIds, 
    			Model model, Authentication authentication) throws Exception {
    	
    	CustomAuthUser user = (CustomAuthUser) authentication.getPrincipal();
    	long studentId = user.getId();
    	for(String sectionId: sectionIds) {
    		studentRegistrationService.registerToSection(studentId, Long.valueOf(sectionId));
    	}
        return "redirect:/student/registration";
    }

}
