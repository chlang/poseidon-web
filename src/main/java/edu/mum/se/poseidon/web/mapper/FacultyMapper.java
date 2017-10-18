package edu.mum.se.poseidon.web.mapper;

import org.springframework.stereotype.Component;

import edu.mum.se.poseidon.web.models.FacultyModel;
import edu.mum.se.poseidon.web.services.dto.FacultyDto;

@Component
public class FacultyMapper {

	public FacultyDto getFacultyDto(FacultyModel faculty) {
		FacultyDto facultyDto = new FacultyDto();
		facultyDto.setId(faculty.getId());
		facultyDto.setFirstName(faculty.getFirstName());
		facultyDto.setLastName(faculty.getLastName());
		return facultyDto;
	}
}
