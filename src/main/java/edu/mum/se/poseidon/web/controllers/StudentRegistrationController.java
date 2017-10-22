package edu.mum.se.poseidon.web.controllers;

import edu.mum.se.poseidon.web.mapper.RegistrationMapper;
import edu.mum.se.poseidon.web.mapper.SectionMapper;
import edu.mum.se.poseidon.web.models.registration.RegistrationModel;
import edu.mum.se.poseidon.web.services.StudentRegistrationService;
import edu.mum.se.poseidon.web.services.dto.SectionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String index(@PathVariable long studentId, Model model) throws Exception {
        List<SectionDto> sectionDtoList = studentRegistrationService.getAvailableSections(studentId);
        List<RegistrationModel> registrationModelList = registrationMapper.getRegistrationModelFrom(sectionDtoList);
        model.addAttribute("registrationModels", registrationModelList);
        return "/student/registration";
    }

    @RequestMapping(path = "/student/registration", method = RequestMethod.POST)
    public String editPOST(@PathVariable long studentId, @PathVariable long sectionId, Model model) throws Exception {
        studentRegistrationService.registerToSection(studentId, sectionId);
        return "redirect:/admin/section";
    }

}
