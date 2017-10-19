package edu.mum.se.poseidon.web.mapper;

import edu.mum.se.poseidon.web.models.FacultyModel;
import edu.mum.se.poseidon.web.services.dto.FacultyProfileDto;
import org.springframework.stereotype.Component;

@Component
public class FacultyToDtoMapper {

    public FacultyProfileDto getFacultyDtoFrom(FacultyModel studentModel) {
        FacultyProfileDto dto = new FacultyProfileDto();
        dto.setFirstName(studentModel.getFirstName());
        dto.setLastName(studentModel.getLastName());
        dto.setUsername(studentModel.getUsername());
        dto.setPassword(studentModel.getPassword());
        dto.setType(studentModel.getType());

        return dto;
    }
}
