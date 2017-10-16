package edu.mum.se.poseidon.web.mapper;

import edu.mum.se.poseidon.web.models.StudentModel;
import edu.mum.se.poseidon.web.services.dto.StudentDto;
import org.springframework.stereotype.Component;

@Component
public class StudentFromDtoMapper {

    public StudentModel getStudentModelFrom(StudentDto studentDto) {
        if(studentDto == null) {
            return null;
        }
        StudentModel studentModel = new StudentModel();
        studentModel.setId(studentDto.getId());
        studentModel.setFirstName(studentDto.getFirstName());
        studentModel.setLastName(studentDto.getLastName());
        studentModel.setUsername(studentDto.getUsername());
        studentModel.setPassword(studentDto.getPassword());

        return studentModel;
    }
}
