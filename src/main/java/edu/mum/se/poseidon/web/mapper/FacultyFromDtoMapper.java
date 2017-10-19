package edu.mum.se.poseidon.web.mapper;

import edu.mum.se.poseidon.web.models.CourseInfo;
import edu.mum.se.poseidon.web.models.FacultyModel;
import edu.mum.se.poseidon.web.models.FacultyProfileModel;
import edu.mum.se.poseidon.web.services.dto.FacultyProfileDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FacultyFromDtoMapper {


    public FacultyModel getFacultyModelFrom(long id, FacultyProfileDto facultyDto) {
        if(facultyDto == null) {
            return null;
        }
        FacultyModel studentModel = new FacultyModel();
        studentModel.setId(id);
        studentModel.setFirstName(facultyDto.getFirstName());
        studentModel.setLastName(facultyDto.getLastName());
        studentModel.setUsername(facultyDto.getUsername());
        studentModel.setPassword(facultyDto.getPassword());

        return studentModel;
    }

    public FacultyProfileModel getFacultyProfileModelFrom(long id, FacultyProfileDto facultyDto, List<CourseInfo> courseInfoList) {
        if(facultyDto == null) {
            return null;
        }
        FacultyProfileModel profileModel = new FacultyProfileModel();
        profileModel.setId(id);
        profileModel.setFirstName(facultyDto.getFirstName());
        profileModel.setLastName(facultyDto.getLastName());
        profileModel.setUsername(facultyDto.getUsername());
        profileModel.setPassword(facultyDto.getPassword());
        profileModel.setCourseList(courseInfoList);

        return profileModel;
    }
}
