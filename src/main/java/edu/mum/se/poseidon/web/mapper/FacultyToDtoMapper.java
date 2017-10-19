package edu.mum.se.poseidon.web.mapper;

import edu.mum.se.poseidon.web.models.FacultyModel;
import edu.mum.se.poseidon.web.models.FacultyProfileModel;
import edu.mum.se.poseidon.web.services.dto.CourseInfoDto;
import edu.mum.se.poseidon.web.services.dto.FacultyProfileDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FacultyToDtoMapper {

    public FacultyProfileDto getFacultyProfileDtoFrom(FacultyProfileModel studentModel,
                                                      List<CourseInfoDto> courseInfoDtoList) {
        FacultyProfileDto dto = new FacultyProfileDto();
        dto.setFirstName(studentModel.getFirstName());
        dto.setLastName(studentModel.getLastName());
        dto.setUsername(studentModel.getUsername());
        dto.setPassword(studentModel.getPassword());
        dto.setCourseList(courseInfoDtoList);

        return dto;
    }
}
