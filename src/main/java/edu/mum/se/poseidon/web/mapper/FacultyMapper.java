package edu.mum.se.poseidon.web.mapper;

import org.springframework.stereotype.Component;

import edu.mum.se.poseidon.web.models.FacultyModel;
import edu.mum.se.poseidon.web.services.dto.FacultyProfileDto;

@Component
public class FacultyMapper {

	public FacultyProfileDto getFacultyDto(FacultyModel faculty) {
		FacultyProfileDto facultyDto = new FacultyProfileDto();
		facultyDto.setId(faculty.getId());
		facultyDto.setFirstName(faculty.getFirstName());
		facultyDto.setLastName(faculty.getLastName());
		facultyDto.setUsername(faculty.getUsername());
		facultyDto.setPassword(faculty.getPassword());
		facultyDto.setType(faculty.getType());
		return facultyDto;
	}
	
	public FacultyModel getFaculty(FacultyProfileDto facultyDto) {
		FacultyModel faculty = new FacultyModel();
		faculty.setId(facultyDto.getId());
		faculty.setFirstName(facultyDto.getFirstName());
		faculty.setLastName(facultyDto.getLastName());
		faculty.setUsername(facultyDto.getUsername());
		faculty.setPassword(facultyDto.getPassword());
		faculty.setType(facultyDto.getType());
		return faculty;
	}
}
