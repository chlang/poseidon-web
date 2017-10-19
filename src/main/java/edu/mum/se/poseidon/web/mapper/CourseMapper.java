package edu.mum.se.poseidon.web.mapper;

import java.util.List;
import java.util.stream.Collectors;

import edu.mum.se.poseidon.web.models.CourseInfo;
import edu.mum.se.poseidon.web.services.dto.CourseInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.se.poseidon.web.models.Course;
import edu.mum.se.poseidon.web.services.dto.CourseDto;

@Component
public class CourseMapper {

	private FacultyMapper facultyMapper;
	private PrerequisiteMapper prerequisiteMapper;
	
	@Autowired
	public CourseMapper(FacultyMapper facultyMapper, PrerequisiteMapper prerequisiteMapper) {
		this.facultyMapper = facultyMapper;
		this.prerequisiteMapper = prerequisiteMapper;
	}
	
	public CourseDto getCourseDto(Course course) {
		CourseDto courseDto = new CourseDto();
		courseDto.setId(course.getId());
		courseDto.setName(course.getName());
		courseDto.setNumber(course.getNumber());
		
		courseDto.setPrerequisites(course.getPrerequisites()
					.stream()
					.map(p -> prerequisiteMapper.getPrerequisiteDto(p))
					.collect(Collectors.toList()));

		// TODO can we change the object name here?
        // or there should be profileDto? - Yuriy
		courseDto.setFaculties(course.getFaculties()
					.stream()
					.map(f -> facultyMapper.getFacultyDto(f))
					.collect(Collectors.toList()));
		return courseDto;
	}
	
	public Course getCourse(CourseDto courseDto) {
		Course course = new Course();
		course.setId(courseDto.getId());
		course.setName(courseDto.getName());
		course.setNumber(courseDto.getNumber());
		
		course.setPrerequisites(courseDto.getPrerequisites()
					.stream()
					.map(p -> prerequisiteMapper.getCourse(p))
					.collect(Collectors.toList()));
		course.setFaculties(courseDto.getFaculties()
					.stream()
					.map(f -> facultyMapper.getFaculty(f))
					.collect(Collectors.toList()));
		return course;
	}

	public List<CourseInfo> getCourseInfoList(List<CourseInfoDto> courseList) {
		if(courseList == null) {
			return null;
		}

		return courseList.stream()
				.map(dto -> getCourseInfo(dto))
				.filter(info -> info != null)
				.collect(Collectors.toList());
	}

	public CourseInfo getCourseInfo(CourseInfoDto dto) {
		if(dto == null) {
			return null;
		}

		CourseInfo info = new CourseInfo();
		info.setId(dto.getId());
		info.setName(dto.getName());
		info.setNumber(dto.getNumber());

		return info;
	}

    public List<CourseInfoDto> getCourseInfoDtoList(List<CourseInfo> courseList) {
        if(courseList == null) {
            return null;
        }

        return courseList.stream()
                .map(course -> getCourseInfoDto(course))
                .filter(dto -> dto != null)
                .collect(Collectors.toList());
    }

    public CourseInfoDto getCourseInfoDto(CourseInfo courseInfo) {
	    if(courseInfo == null) {
	        return null;
        }

        CourseInfoDto dto = new CourseInfoDto();
	    dto.setId(courseInfo.getId());
	    dto.setName(courseInfo.getName());
	    dto.setNumber(courseInfo.getNumber());

	    return dto;
    }

	public List<Course> getCourseList(List<CourseDto> courses) {
		if(courses == null) {
			return null;
		}

		return courses.stream()
				.map(dto -> getCourse(dto))
				.filter(c -> c != null)
				.collect(Collectors.toList());
	}
}