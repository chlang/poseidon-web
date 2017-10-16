package edu.mum.se.poseidon.web.services;

import edu.mum.se.poseidon.web.Config;
import edu.mum.se.poseidon.web.services.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StudentService {

    private RestTemplate restTemplate;
    private Config config;

    private String servicePath = "/students";

    @Autowired
    public StudentService(RestTemplate restTemplate, Config config) {
        this.restTemplate = restTemplate;
        this.config = config;
    }

    public StudentDto getStudent(long studentId) throws Exception {
        String url = config.getBaseUrl() + servicePath + "/{studentId}";
        ResponseEntity<StudentDto> response = restTemplate.getForEntity(url, StudentDto.class, studentId);
        if ( HttpStatus.OK != response.getStatusCode() ) {
            throw new Exception("Some error occured: " + response.getStatusCodeValue());
        }
        return response.getBody();
    }

    public StudentDto updateProfile(StudentDto dto) {
        return dto;
    }
}
