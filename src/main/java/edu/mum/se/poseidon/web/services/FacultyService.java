package edu.mum.se.poseidon.web.services;

import edu.mum.se.poseidon.web.Config;
import edu.mum.se.poseidon.web.services.dto.FacultyDto;
import java.util.Arrays;
import java.util.List;

import edu.mum.se.poseidon.web.services.dto.FacultyProfileDto;
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

    private final String servicePath = "/faculties";

    @Autowired
    public FacultyService(RestTemplate restTemplate, Config config) {
    	this.restTemplate = restTemplate;
    	this.config = config;
    }

    public List<FacultyDto> getFaculties() throws Exception {
        url = config.getBaseUrl() + servicePath;
        ResponseEntity<FacultyDto[]> response = restTemplate.getForEntity(url, FacultyDto[].class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception("Some error occured: " + response.getStatusCodeValue());
        }
        return Arrays.asList(response.getBody());
    }

    public FacultyProfileDto getFaculty(long facultyId)
            throws Exception {
        String url = config.getBaseUrl() + servicePath + "/{facultyId}";
        ResponseEntity<FacultyProfileDto> response = restTemplate.getForEntity(url, FacultyProfileDto.class, facultyId);
        if ( HttpStatus.OK != response.getStatusCode() ) {
            throw new RuntimeException("Some error occured: " + response.getStatusCodeValue());
        }
        return response.getBody();
    }

    public FacultyProfileDto updateProfile(long facultyId, FacultyProfileDto dto)
            throws Exception {
        String url = config.getBaseUrl() + servicePath + "/" + facultyId;
        ResponseEntity<FacultyProfileDto> response = restTemplate.postForEntity(url, dto, FacultyProfileDto.class);
        if ( HttpStatus.OK != response.getStatusCode() ) {
            throw new RuntimeException("Some error occured: " + response.getStatusCodeValue());
        }
        return response.getBody();
    }
}
