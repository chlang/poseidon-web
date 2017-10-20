package edu.mum.se.poseidon.web.services;

import edu.mum.se.poseidon.web.Config;
import edu.mum.se.poseidon.web.services.dto.AdminScheduleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Yuriy Yugay on 10/20/2017.
 *
 * @author Yuriy Yugay
 */
@Service
public class AdminScheduleService {

    private RestTemplate restTemplate;
    private Config config;

    private String servicePath = "/schedules";

    @Autowired
    public AdminScheduleService(RestTemplate restTemplate, Config config) {
        this.restTemplate = restTemplate;
        this.config = config;
    }

    public List<AdminScheduleDto> getAllSchedule() {
        String url = config.getBaseUrl() + servicePath + "/all";
        ResponseEntity<AdminScheduleDto[]> response = restTemplate.getForEntity(url, AdminScheduleDto[].class);
        if ( HttpStatus.OK != response.getStatusCode() ) {
            throw new RuntimeException("Some error occured: " + response.getStatusCodeValue());
        }
        return Arrays.asList(response.getBody());
    }
}
