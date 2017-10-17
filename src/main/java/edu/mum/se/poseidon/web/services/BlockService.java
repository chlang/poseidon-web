package edu.mum.se.poseidon.web.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import edu.mum.se.poseidon.web.Config;
import edu.mum.se.poseidon.web.services.dto.BlockDto;

@Service
public class BlockService {

	private String url;
	private RestTemplate restTemplate;
    private Config config;
    
    @Autowired
    public BlockService(RestTemplate restTemplate, Config config) {
    	this.restTemplate = restTemplate;
    	this.config = config;
    }
    
    public BlockDto getBlock(long blockId) throws Exception {
    	url = config.getBaseUrl() + "/blocks/{blockId}";
    	ResponseEntity<BlockDto> response = restTemplate.getForEntity(url, BlockDto.class, blockId);
    	if(response.getStatusCode() != HttpStatus.OK) {
    		throw new Exception("Some error occured: " + response.getStatusCodeValue());
    	}
    	return response.getBody();
    }
    
    public List<BlockDto> getBlocks() throws Exception {
    	url = config.getBaseUrl() + "/blocks";
    	ResponseEntity<BlockDto[]> response = restTemplate.getForEntity(url, BlockDto[].class);
    	if(response.getStatusCode() != HttpStatus.OK) {
    		throw new Exception("Some error occured: " + response.getStatusCodeValue());
    	}
    	return Arrays.asList(response.getBody());
    }
    
    public BlockDto createBlock(BlockDto blockDto) throws Exception {
    	url = config.getBaseUrl() + "/blocks/create";
    	HttpEntity<BlockDto> entity = new HttpEntity<BlockDto>(blockDto);
    	ResponseEntity<BlockDto> response = restTemplate.exchange(url, 
	    		HttpMethod.POST, entity, BlockDto.class);
    	if(response.getStatusCode() != HttpStatus.OK) {
    		throw new Exception("Some error occured: " + response.getStatusCodeValue());
    	}
    	return response.getBody();
    }
    
    public BlockDto editBlock(BlockDto blockDto) throws Exception {
    	url = config.getBaseUrl() + "/blocks/edit";
    	HttpEntity<BlockDto> entity = new HttpEntity<BlockDto>(blockDto);
    	ResponseEntity<BlockDto> response = restTemplate.exchange(url, 
	    		HttpMethod.POST, entity, BlockDto.class);
    	if(response.getStatusCode() != HttpStatus.OK) {
    		throw new Exception("Some error occured: " + response.getStatusCodeValue());
    	}
    	return response.getBody();
    }
    
    public BlockDto deleteBlock(long blockId) throws Exception {
    	url = config.getBaseUrl() + "/blocks/delete/{blockId}";
    	ResponseEntity<BlockDto> response = restTemplate.getForEntity(url, BlockDto.class, blockId);
    	if(response.getStatusCode() != HttpStatus.OK) {
    		throw new Exception("Some error occured: " + response.getStatusCodeValue());
    	}
    	return response.getBody();
    }
}
