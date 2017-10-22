package edu.mum.se.poseidon.web.services;

import edu.mum.se.poseidon.web.Config;
import edu.mum.se.poseidon.web.services.dto.SectionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class StudentRegistrationService {
    private String url;
    private RestTemplate restTemplate;
    private Config config;
    private static final Logger log = LoggerFactory.getLogger(StudentRegistrationService.class);

    @Autowired
    public StudentRegistrationService(RestTemplate restTemplate, Config config) {
        this.restTemplate = restTemplate;
        this.config = config;
    }

    public List<SectionDto> getAvailableSections(Long studentId) throws Exception {
        url = config.getBaseUrl() + "/student/{studentId}/section/available";
        ResponseEntity<SectionDto[]> response = restTemplate.getForEntity(url, SectionDto[].class, studentId);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception("Some error occured: " + response.getStatusCodeValue());
        }
        return Arrays.asList(response.getBody());
    }

    public SectionDto registerToSection(Long studentId, Long sectionId) throws Exception {
        url = config.getBaseUrl() + "/student/{studentId}/section/{sectionId}";
        ResponseEntity<SectionDto> response = restTemplate.getForEntity(url, SectionDto.class, studentId, sectionId);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception("Some error occured: " + response.getStatusCodeValue());
        }
        return response.getBody();
    }
}
