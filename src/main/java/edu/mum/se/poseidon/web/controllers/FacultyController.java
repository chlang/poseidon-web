package edu.mum.se.poseidon.web.controllers;

import edu.mum.se.poseidon.web.CustomAuthUser;
import edu.mum.se.poseidon.web.mapper.CourseMapper;
import edu.mum.se.poseidon.web.mapper.FacultyFromDtoMapper;
import edu.mum.se.poseidon.web.mapper.FacultyToDtoMapper;
import edu.mum.se.poseidon.web.models.Course;
import edu.mum.se.poseidon.web.models.CourseInfo;
import edu.mum.se.poseidon.web.models.FacultyModel;
import edu.mum.se.poseidon.web.models.FacultyProfileModel;
import edu.mum.se.poseidon.web.services.CourseService;
import edu.mum.se.poseidon.web.services.FacultyService;
import edu.mum.se.poseidon.web.services.dto.CourseDto;
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

    private CourseService courseService;
    private FacultyService facultyService;
    private FacultyFromDtoMapper facultyFromDtoMapper;
    private FacultyToDtoMapper facultyToDtoMapper;
    private CourseMapper courseMapper;

    @Autowired
    public FacultyController(FacultyService facultyService,
                             CourseService courseService,
                             FacultyFromDtoMapper facultyFromDtoMapper,
                             FacultyToDtoMapper facultyToDtoMapper,
                             CourseMapper courseMapper) {
        this.facultyService = facultyService;
        this.facultyFromDtoMapper = facultyFromDtoMapper;
        this.facultyToDtoMapper = facultyToDtoMapper;
        this.courseMapper = courseMapper;
        this.courseService = courseService;
    }


    @RequestMapping(path = "/faculty/profile", method = RequestMethod.GET)
    public String getFacultyProfile(Model model, Authentication authentication) {
        try {
            CustomAuthUser user = (CustomAuthUser) authentication.getPrincipal();

            FacultyProfileDto facultyDto = facultyService.getFaculty(user.getId());

            List<Course> courseList = courseMapper.getCourseList(courseService.getCourses());

            List<CourseInfo> courseInfoList = courseMapper.getCourseInfoList(facultyDto.getCourseList());
            FacultyProfileModel mod = facultyFromDtoMapper.getFacultyProfileModelFrom(user.getId(), facultyDto, courseInfoList);
            model.addAttribute("faculty", mod);
            // TODO need populate in html page
            model.addAttribute("allCourses", courseList);
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
