package edu.mum.se.poseidon.web.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import edu.mum.se.poseidon.web.Config;
import edu.mum.se.poseidon.web.services.dto.CourseDto;

@Service
public class CourseService {

	private String url;
	private RestTemplate restTemplate;
    private Config config;
    
    @Autowired
    public CourseService(RestTemplate restTemplate, Config config) {
    	this.restTemplate = restTemplate;
    	this.config = config;
    }
    
    public CourseDto getCourse(long courseId) throws Exception {
    	url = config.getBaseUrl() + "/courses/{courseId}";
    	ResponseEntity<CourseDto> response = restTemplate.getForEntity(url, CourseDto.class, courseId);
    	if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception("Some error occured: " + response.getStatusCodeValue());
        }
        return response.getBody();
    }
    
    public List<CourseDto> getCourses() throws Exception {
    	url = config.getBaseUrl() + "/courses";
    	ResponseEntity<CourseDto[]> response = restTemplate.getForEntity(url, CourseDto[].class);
    	if(response.getStatusCode() != HttpStatus.OK) {
    		throw new Exception("Some error occured: " + response.getStatusCodeValue());
    	}
    	return Arrays.asList(response.getBody());
    }
    
    public CourseDto createCourse(CourseDto courseDto) throws Exception {
        url = config.getBaseUrl() + "/courses/create";
        HttpEntity<CourseDto> entity = new HttpEntity<CourseDto>(courseDto);
        ResponseEntity<CourseDto> response = restTemplate.exchange(url,
                HttpMethod.POST, entity, CourseDto.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception("Some error occured: " + response.getStatusCodeValue());
        }
        return response.getBody();
    }
    
    public CourseDto editCourse(CourseDto courseDto) throws Exception {
    	url = config.getBaseUrl() + "/courses/edit";
    	HttpEntity<CourseDto> entity = new HttpEntity<CourseDto>(courseDto);
    	ResponseEntity<CourseDto> response = restTemplate.exchange(url, 
	    		HttpMethod.POST, entity, CourseDto.class);
    	if(response.getStatusCode() != HttpStatus.OK) {
    		throw new Exception("Some error occured: " + response.getStatusCodeValue());
    	}
    	return response.getBody();
    }
    
    public CourseDto deleteCourse(long courseId) throws Exception {
    	url = config.getBaseUrl() + "/courses/{courseId}/delete";
    	ResponseEntity<CourseDto> response = restTemplate.getForEntity(url, CourseDto.class, courseId);
    	if(response.getStatusCode() != HttpStatus.OK) {
    		throw new Exception("Some error occured: " + response.getStatusCodeValue());
    	}
    	return response.getBody();
    }
}
