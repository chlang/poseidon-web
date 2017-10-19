package edu.mum.se.poseidon.web.services;

import edu.mum.se.poseidon.web.Config;
import edu.mum.se.poseidon.web.services.dto.FacultySectionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class FacultyScheduleService {

    private RestTemplate restTemplate;
    private Config config;

    private String servicePath = "/faculties";

    @Autowired
    public FacultyScheduleService(RestTemplate restTemplate, Config config) {
        this.restTemplate = restTemplate;
        this.config = config;
    }

    public List<FacultySectionDto> getFacultySchedule(long facultyId) {
        String url = config.getBaseUrl() + servicePath + "/{facultyId}/schedule";
        ResponseEntity<FacultySectionDto[]> response = restTemplate.getForEntity(url, FacultySectionDto[].class, facultyId);
        if ( HttpStatus.OK != response.getStatusCode() ) {
            throw new RuntimeException("Some error occured: " + response.getStatusCodeValue());
        }
        return Arrays.asList(response.getBody());
    }
}
