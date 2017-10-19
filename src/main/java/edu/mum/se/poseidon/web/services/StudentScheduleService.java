package edu.mum.se.poseidon.web.services;

import edu.mum.se.poseidon.web.Config;
import edu.mum.se.poseidon.web.services.dto.StudentSectionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class StudentScheduleService {

    private RestTemplate restTemplate;
    private Config config;

    private String servicePath = "/students";

    @Autowired
    public StudentScheduleService(RestTemplate restTemplate, Config config) {
        this.restTemplate = restTemplate;
        this.config = config;
    }

    public List<StudentSectionDto> getStudentSchedule(long studentId) {
        String url = config.getBaseUrl() + servicePath + "/{studentId}/schedule";
        ResponseEntity<StudentSectionDto[]> response = restTemplate.getForEntity(url, StudentSectionDto[].class, studentId);
        if ( HttpStatus.OK != response.getStatusCode() ) {
            throw new RuntimeException("Some error occured: " + response.getStatusCodeValue());
        }
        return Arrays.asList(response.getBody());
    }
}
