package edu.mum.se.poseidon.web.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    
    public List<CourseDto> getCourses() throws Exception {
    	url = config.getBaseUrl() + "/courses";
    	ResponseEntity<CourseDto[]> response = restTemplate.getForEntity(url, CourseDto[].class);
    	if(response.getStatusCode() != HttpStatus.OK) {
    		throw new Exception("Some error occured: " + response.getStatusCodeValue());
    	}
    	return Arrays.asList(response.getBody());
    }
}
