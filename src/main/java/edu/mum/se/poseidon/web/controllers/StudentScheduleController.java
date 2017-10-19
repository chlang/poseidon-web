package edu.mum.se.poseidon.web.controllers;

import edu.mum.se.poseidon.web.CustomAuthUser;
import edu.mum.se.poseidon.web.mapper.SectionMapper;
import edu.mum.se.poseidon.web.models.StudentSectionModel;
import edu.mum.se.poseidon.web.services.StudentScheduleService;
import edu.mum.se.poseidon.web.services.dto.StudentSectionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class StudentScheduleController {

    private StudentScheduleService studentScheduleService;
    private SectionMapper sectionMapper;

    @Autowired
    public StudentScheduleController(StudentScheduleService studentScheduleService,
                                     SectionMapper sectionMapper) {
        this.studentScheduleService = studentScheduleService;
        this.sectionMapper = sectionMapper;
    }

    @RequestMapping(path = "/student/schedule", method = RequestMethod.GET)
    public String getSchedule(Model model, Authentication authentication) throws Exception {
        try {
            CustomAuthUser user = (CustomAuthUser) authentication.getPrincipal();

            List<StudentSectionDto> studentSectionDtoList
                    = studentScheduleService.getStudentSchedule(user.getId());

//            List<StudentSectionModel> sectionModelList = sectionMapper.getStudentSectionModelList(studentSectionDtoList);
            List<StudentSectionModel> sectionModelList = sectionMapper.getStudentSectionModelSTUBList();

            model.addAttribute("sections", sectionModelList);
            return "student/schedule";
        } catch(Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }
}
