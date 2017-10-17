package edu.mum.se.poseidon.web.services;

import edu.mum.se.poseidon.web.Config;
import edu.mum.se.poseidon.web.services.dto.SectionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class SectionService {

    private String url;
    private RestTemplate restTemplate;
    private Config config;
    private static final Logger logger = LoggerFactory.getLogger(SectionService.class);

    @Autowired
    public SectionService(RestTemplate restTemplate, Config config) {
        this.restTemplate = restTemplate;
        this.config = config;
    }

    public SectionDto getSection(Long sectionId) throws Exception {
        url = config.getBaseUrl() + "/sections/{sectionId}";
        ResponseEntity<SectionDto> response = restTemplate.getForEntity(url, SectionDto.class, sectionId);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception("Some error occured: " + response.getStatusCodeValue());
        }
        return response.getBody();
    }

    public List<SectionDto> getSections() throws Exception {
        url = config.getBaseUrl() + "/sections";
        ResponseEntity<SectionDto[]> response = restTemplate.getForEntity(url, SectionDto[].class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception("Some error occured: " + response.getStatusCodeValue());
        }
        return Arrays.asList(response.getBody());
    }

    public SectionDto editSection(SectionDto sectionDto) throws Exception {
        url = config.getBaseUrl() + "/sections/edit";
        HttpEntity<SectionDto> entity = new HttpEntity<>(sectionDto);
        ResponseEntity<SectionDto> response = restTemplate.exchange(url,
                HttpMethod.POST, entity, SectionDto.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception("Some error occured: " + response.getStatusCodeValue());
        }
        return response.getBody();
    }

    public SectionDto deleteSection(long sectionId) throws Exception {
        url = config.getBaseUrl() + "/sections/delete/{sectionId}";
        ResponseEntity<SectionDto> response = restTemplate.getForEntity(url, SectionDto.class, sectionId);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception("Some error occured: " + response.getStatusCodeValue());
        }
        return response.getBody();
    }
}
