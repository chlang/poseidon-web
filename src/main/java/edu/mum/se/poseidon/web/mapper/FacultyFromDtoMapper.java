package edu.mum.se.poseidon.web.mapper;

import edu.mum.se.poseidon.web.models.FacultyModel;
import edu.mum.se.poseidon.web.services.dto.FacultyDto;
import edu.mum.se.poseidon.web.services.dto.FacultyProfileDto;
import org.springframework.stereotype.Component;

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
}
