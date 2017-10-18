package edu.mum.se.poseidon.web.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import edu.mum.se.poseidon.web.Config;
import edu.mum.se.poseidon.web.services.dto.FacultyDto;

@Service
public class FacultyService {

	private String url;
	private RestTemplate restTemplate;
    private Config config;
    
    @Autowired
    public FacultyService(RestTemplate restTemplate, Config config) {
    	this.restTemplate = restTemplate;
    	this.config = config;
    }
    
    public List<FacultyDto> getFaculties() throws Exception {
    	url = config.getBaseUrl() + "/faculties";
    	ResponseEntity<FacultyDto[]> response = restTemplate.getForEntity(url, FacultyDto[].class);
    	if(response.getStatusCode() != HttpStatus.OK) {
    		throw new Exception("Some error occured: " + response.getStatusCodeValue());
    	}
    	return Arrays.asList(response.getBody());
    }
}
