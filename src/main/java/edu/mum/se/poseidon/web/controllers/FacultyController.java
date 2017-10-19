package edu.mum.se.poseidon.web.controllers;

import edu.mum.se.poseidon.web.CustomAuthUser;
import edu.mum.se.poseidon.web.mapper.CourseMapper;
import edu.mum.se.poseidon.web.mapper.FacultyFromDtoMapper;
import edu.mum.se.poseidon.web.mapper.FacultyToDtoMapper;
import edu.mum.se.poseidon.web.models.CourseInfo;
import edu.mum.se.poseidon.web.models.FacultyModel;
import edu.mum.se.poseidon.web.models.FacultyProfileModel;
import edu.mum.se.poseidon.web.services.FacultyService;
import edu.mum.se.poseidon.web.services.dto.CourseInfoDto;
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
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class FacultyController {

    private FacultyService facultyService;
    private FacultyFromDtoMapper facultyFromDtoMapper;
    private FacultyToDtoMapper facultyToDtoMapper;
    private CourseMapper courseMapper;

    @Autowired
    public FacultyController(FacultyService facultyService,
                             FacultyFromDtoMapper facultyFromDtoMapper,
                             FacultyToDtoMapper facultyToDtoMapper,
                             CourseMapper courseMapper) {
        this.facultyService = facultyService;
        this.facultyFromDtoMapper = facultyFromDtoMapper;
        this.facultyToDtoMapper = facultyToDtoMapper;
        this.courseMapper = courseMapper;
    }


    @RequestMapping(path = "/faculty/profile", method = RequestMethod.GET)
    public String getFacultyProfile(Model model, Authentication authentication) {
        try {
            CustomAuthUser user = (CustomAuthUser) authentication.getPrincipal();

            FacultyProfileDto facultyDto = facultyService.getFaculty(user.getId());

            /*
            // STUB START
            CourseInfoDto dto1 = new CourseInfoDto();
            dto1.setId(23l);
            dto1.setName("Algorithm");
            dto1.setNumber(485);

            CourseInfoDto dto2 = new CourseInfoDto();
            dto2.setId(35l);
            dto2.setName("Advanced Software Development");
            dto2.setNumber(525);

            CourseInfoDto dto3 = new CourseInfoDto();
            dto3.setId(55l);
            dto3.setName("Machine Learning");
            dto3.setNumber(540);

            facultyDto.setCourseList(Stream.of(dto1,dto2,dto3).collect(Collectors.toList()));
            // STUB END
            */

            List<CourseInfo> courseInfoList = courseMapper.getCourseInfoList(facultyDto.getCourseList());
            FacultyProfileModel mod = facultyFromDtoMapper.getFacultyProfileModelFrom(user.getId(), facultyDto, courseInfoList);
            model.addAttribute("faculty", mod);
            return "faculty/profile";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(path = "faculty/{id}/profile", method = RequestMethod.POST)
    public String updateFacultyProfile(@PathVariable long id,
                                       @ModelAttribute("user") @Valid FacultyProfileModel facultyModel,
                                       Model model) {
        try {
            List<CourseInfoDto> courseInfoDtoList = courseMapper.getCourseInfoDtoList(facultyModel.getCourseList());
            FacultyProfileDto dto = facultyToDtoMapper.getFacultyProfileDtoFrom(facultyModel, courseInfoDtoList);
            FacultyProfileDto updatedDto = facultyService.updateProfile(id, dto);

            List<CourseInfo> courseInfoList = courseMapper.getCourseInfoList(updatedDto.getCourseList());
            facultyModel = facultyFromDtoMapper.getFacultyProfileModelFrom(id, updatedDto, courseInfoList);
            model.addAttribute("faculty", facultyModel);
            return "faculty/profile";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }
}
