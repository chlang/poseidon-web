package edu.mum.se.poseidon.web.controllers;

import edu.mum.se.poseidon.web.CustomAuthUser;
import edu.mum.se.poseidon.web.mapper.SectionMapper;
import edu.mum.se.poseidon.web.models.FacultySectionModel;
import edu.mum.se.poseidon.web.services.FacultyScheduleService;
import edu.mum.se.poseidon.web.services.dto.FacultySectionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class FacultyScheduleController {

    private FacultyScheduleService facultyScheduleService;
    private SectionMapper sectionMapper;

    @Autowired
    public FacultyScheduleController(FacultyScheduleService facultyScheduleService,
                                     SectionMapper sectionMapper) {
        this.facultyScheduleService = facultyScheduleService;
        this.sectionMapper = sectionMapper;
    }

    @RequestMapping(path = "/faculty/schedule", method = RequestMethod.GET)
    public String getSchedule(Model model, Authentication authentication) throws Exception {
        try {
            CustomAuthUser user = (CustomAuthUser) authentication.getPrincipal();

            List<FacultySectionDto> facultySectionDtoList
                    = facultyScheduleService.getFacultySchedule(user.getId());

            List<FacultySectionModel> sectionModelList = sectionMapper.getFacultySectionModelList(facultySectionDtoList);

            model.addAttribute("sections", sectionModelList);
            return "faculty/schedule";
        } catch(Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }
}
