package edu.mum.se.poseidon.web.services;

import edu.mum.se.poseidon.web.Config;
import edu.mum.se.poseidon.web.services.dto.EntryDto;
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
public class EntryService {
    private String url;
    private RestTemplate restTemplate;
    private Config config;
    private static final Logger log = LoggerFactory.getLogger(EntryService.class);

    @Autowired
    public EntryService(RestTemplate restTemplate, Config config) {
        this.restTemplate = restTemplate;
        this.config = config;
    }

    public EntryDto getEntry(Long entryId) throws Exception {
        url = config.getBaseUrl() + "/entries/{entryId}";
        ResponseEntity<EntryDto> response = restTemplate.getForEntity(url, EntryDto.class, entryId);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception("Some error occured: " + response.getStatusCodeValue());
        }
        return response.getBody();
    }

    public List<EntryDto> getEntries() throws Exception {
        url = config.getBaseUrl() + "/entries";
        ResponseEntity<EntryDto[]> response = restTemplate.getForEntity(url, EntryDto[].class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception("Some error occured: " + response.getStatusCodeValue());
        }
        return Arrays.asList(response.getBody());
    }

    public EntryDto createEntry(EntryDto entryDto) throws Exception {
        url = config.getBaseUrl() + "/entries/create";
        HttpEntity<EntryDto> entity = new HttpEntity<>(entryDto);
        ResponseEntity<EntryDto> response = restTemplate.exchange(url,
                HttpMethod.POST, entity, EntryDto.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception("Some error occured: " + response.getStatusCodeValue());
        }
        return response.getBody();
    }

    public EntryDto editEntry(EntryDto entryDto) throws Exception {
        url = config.getBaseUrl() + "/entries/edit";
        HttpEntity<EntryDto> entity = new HttpEntity<>(entryDto);
        ResponseEntity<EntryDto> response = restTemplate.exchange(url,
                HttpMethod.POST, entity, EntryDto.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception("Some error occured: " + response.getStatusCodeValue());
        }
        return response.getBody();
    }

    public EntryDto deleteEntry(long entryId) throws Exception {
        url = config.getBaseUrl() + "/entries/delete/{entryId}";
        ResponseEntity<EntryDto> response = restTemplate.getForEntity(url, EntryDto.class, entryId);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception("Some error occured: " + response.getStatusCodeValue());
        }
        return response.getBody();
    }
}
