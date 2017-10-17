package edu.mum.se.poseidon.web.services;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import edu.mum.se.poseidon.web.Config;
import edu.mum.se.poseidon.web.services.dto.FacultyDto;
import edu.mum.se.poseidon.web.services.dto.UserDto;

@Service
public class UserService {

	private String url;
	private RestTemplate restTemplate;
    private Config config;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
	
    @Autowired
    public UserService(RestTemplate restTemplate, Config config) {
    	this.restTemplate = restTemplate;
    	this.config = config;
    }
    
    public UserDto getUser(long userId) throws Exception {
    	url = config.getBaseUrl() + "/users/{userId}";
    	ResponseEntity<UserDto> response = restTemplate.getForEntity(url, UserDto.class, userId);
    	if(response.getStatusCode() != HttpStatus.OK) {
    		throw new Exception("Some error occured: " + response.getStatusCodeValue());
    	}
    	return response.getBody();
    }
    
    public List<UserDto> getUsers() throws Exception {
    	url = config.getBaseUrl() + "/users";
    	ResponseEntity<UserDto[]> response = restTemplate.getForEntity(url, UserDto[].class);
    	if(response.getStatusCode() != HttpStatus.OK) {
    		throw new Exception("Some error occured: " + response.getStatusCodeValue());
    	}
    	return Arrays.asList(response.getBody());
    }
    
    public UserDto createUser(UserDto userDto) throws Exception {
    	url = config.getBaseUrl() + "/users/create";
    	HttpEntity<UserDto> entity = new HttpEntity<UserDto>(userDto);
    	ResponseEntity<UserDto> response = restTemplate.exchange(url, 
	    		HttpMethod.POST, entity, UserDto.class);
    	if(response.getStatusCode() != HttpStatus.OK) {
    		throw new Exception("Some error occured: " + response.getStatusCodeValue());
    	}
    	return response.getBody();
    }
    
    public UserDto editUser(UserDto userDto) throws Exception {
    	url = config.getBaseUrl() + "/users/edit";
    	HttpEntity<UserDto> entity = new HttpEntity<UserDto>(userDto);
    	ResponseEntity<UserDto> response = restTemplate.exchange(url, 
	    		HttpMethod.POST, entity, UserDto.class);
    	if(response.getStatusCode() != HttpStatus.OK) {
    		throw new Exception("Some error occured: " + response.getStatusCodeValue());
    	}
    	return response.getBody();
    }
    
    public UserDto deleteUser(long userId) throws Exception {
    	url = config.getBaseUrl() + "/users/{userId}/delete";
    	ResponseEntity<UserDto> response = restTemplate.getForEntity(url, UserDto.class, userId);
    	if(response.getStatusCode() != HttpStatus.OK) {
    		throw new Exception("Some error occured: " + response.getStatusCodeValue());
    	}
    	return response.getBody();
    }
}
