package edu.mum.se.poseidon.web.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import edu.mum.se.poseidon.web.mapper.CourseMapper;
import edu.mum.se.poseidon.web.models.Course;
import edu.mum.se.poseidon.web.services.CourseService;
import edu.mum.se.poseidon.web.services.dto.CourseDto;

@Component
public class CourseFormatter implements Formatter<Course> {

	private CourseService courseService;
	private CourseMapper courseMapper;
	
	@Autowired
	public CourseFormatter(CourseService courseService, CourseMapper courseMapper) {
		this.courseService = courseService;
		this.courseMapper = courseMapper;
	}
	
	@Override
	public String print(Course arg0, Locale arg1) {
		// TODO Auto-generated method stub
		return String.valueOf(arg0.getId());
	}

	@Override
	public Course parse(String arg0, Locale arg1) throws ParseException {
		// TODO Auto-generated method stub
		long course_id = Long.valueOf(arg0);
		try {
			CourseDto dto = courseService.getCourse(course_id);
			return courseMapper.getCourse(dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Course course = new Course();
			course.setId(course_id);
			return course;
		}
	}

}
