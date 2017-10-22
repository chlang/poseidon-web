package edu.mum.se.poseidon.web.services;

import edu.mum.se.poseidon.web.Config;
import edu.mum.se.poseidon.web.services.dto.ScheduleDto;
import edu.mum.se.poseidon.web.services.dto.ScheduleGenerateDto;
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
public class ScheduleService {

    private String url;
    private RestTemplate restTemplate;
    private Config config;

    private String servicePath = "/schedules";

    @Autowired
    public ScheduleService(RestTemplate restTemplate, Config config) {
        this.restTemplate = restTemplate;
        this.config = config;
    }

    // Gets Schedule by ScheduleId
    public ScheduleDto getSchedule(long id) throws Exception {
        url = config.getBaseUrl() + "/schedules/{id}";
        ResponseEntity<ScheduleDto> response = restTemplate.getForEntity(url, ScheduleDto.class, id);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception("Some error occured: " + response.getStatusCodeValue());
        }
        return response.getBody();
    }

    // Gets Schedules
    public List<ScheduleDto> getSchedules() throws Exception {
        url = config.getBaseUrl() + "/schedules";
        ResponseEntity<ScheduleDto[]> response = restTemplate.getForEntity(url, ScheduleDto[].class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception("Some error occured: " + response.getStatusCodeValue());
        }
        return Arrays.asList(response.getBody());
    }

    // Deletes Schedule by ScheduleId
    public void delete(long id) throws Exception {
        url = config.getBaseUrl() + "/schedules/" + id;
        restTemplate.delete(url);
    }

    // Updates Schedule Status
    public ScheduleDto edit(ScheduleDto scheduleDto) throws Exception {
        url = config.getBaseUrl() + "/schedules/edit";

        HttpEntity<ScheduleDto> entity = new HttpEntity<ScheduleDto>(scheduleDto);
        ResponseEntity<ScheduleDto> response = restTemplate.postForEntity(url, entity, ScheduleDto.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception("Some error occured: " + response.getStatusCodeValue());
        }
        return response.getBody();
    }

    // Creates Schedules
    public ScheduleDto create(ScheduleDto dto) throws Exception {
        url = config.getBaseUrl() + "/schedules/create";
        HttpEntity<ScheduleDto> entity = new HttpEntity<ScheduleDto>(dto);
        ResponseEntity<ScheduleDto> response = restTemplate.postForEntity(url, entity, ScheduleDto.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception("Some error occured: " + response.getStatusCodeValue());
        }
        return response.getBody();
    }

    // Generates Schedule
    public ScheduleGenerateDto generate(ScheduleGenerateDto dto) throws Exception {
        url = config.getBaseUrl() + "schedules/generate";
        HttpEntity<ScheduleGenerateDto> entity = new HttpEntity<ScheduleGenerateDto>(dto);

        ResponseEntity<ScheduleGenerateDto> response = restTemplate.postForEntity(url, entity, ScheduleGenerateDto.class);

        if (response.getStatusCode() != HttpStatus.OK)
            throw new Exception("Some error occured: " + response.getStatusCodeValue());

        return response.getBody();
    }
}
