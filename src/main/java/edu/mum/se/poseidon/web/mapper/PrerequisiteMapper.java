package edu.mum.se.poseidon.web.mapper;

import org.springframework.stereotype.Component;

import edu.mum.se.poseidon.web.models.Course;
import edu.mum.se.poseidon.web.services.dto.PrerequisiteDto;

@Component
public class PrerequisiteMapper {

	public PrerequisiteDto getPrerequisiteDto(Course course) {
		PrerequisiteDto dto = new PrerequisiteDto();
		dto.setId(course.getId());
		dto.setName(course.getName());
		dto.setNumber(course.getNumber());
		return dto;
	}
}
