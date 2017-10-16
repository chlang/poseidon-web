package edu.mum.se.poseidon.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import edu.mum.se.poseidon.web.Config;
import edu.mum.se.poseidon.web.services.dto.AuthenticationDto;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Yuriy Yugay on 10/11/2017.
 *
 * @author Yuriy Yugay
 */

@Service
public class LoginService implements UserDetailsService {

	private RestTemplate restTemplate;
	private Config config;

	private String servicePath = "/logins";

	@Autowired
	public LoginService(RestTemplate restTemplate, Config config) {
		this.restTemplate = restTemplate;
		this.config = config;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		String url = config.getBaseUrl() + servicePath + "/{username}";
		ResponseEntity<AuthenticationDto> response = restTemplate.getForEntity(url, AuthenticationDto.class, username);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new UsernameNotFoundException("Some error occured: " + response.getStatusCodeValue());
		}

		AuthenticationDto dto = response.getBody();

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

		for (String role : dto.getRoles()) {
			System.out.println(">>>>>> ROLE: >>>>>>>>>> " + role);
			grantedAuthorities.add(new SimpleGrantedAuthority(role));
		}

		return new org.springframework.security.core.userdetails.User(dto.getUsername(), dto.getPassword(),
				grantedAuthorities);

	}
}
