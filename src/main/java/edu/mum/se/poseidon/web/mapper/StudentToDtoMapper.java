package edu.mum.se.poseidon.web.mapper;

import edu.mum.se.poseidon.web.models.StudentModel;
import edu.mum.se.poseidon.web.services.dto.StudentDto;
import org.springframework.stereotype.Component;

/**
 * Created by Yuriy Yugay on 10/15/2017.
 *
 * @author Yuriy Yugay
 */
@Component
public class StudentToDtoMapper {

    public StudentDto getStudentDtoFrom(StudentModel studentModel) {
        StudentDto dto = new StudentDto();
        dto.setId(studentModel.getId());
        dto.setFirstName(studentModel.getFirstName());
        dto.setLastName(studentModel.getLastName());
        dto.setUsername(studentModel.getUsername());
        dto.setPassword(studentModel.getPassword());

        return dto;
    }
}
