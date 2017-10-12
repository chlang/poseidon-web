package edu.mum.se.poseidon.web.services;

import edu.mum.se.poseidon.web.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Yuriy Yugay on 10/11/2017.
 *
 * @author Yuriy Yugay
 */
@Service
public class LoginService {

    @Autowired
    private RestTemplate restTemplate;

//    public User login(String username, String password) {
//        ResponseEntity<User> response = restTemplate.postForEntity();
//    }
}
