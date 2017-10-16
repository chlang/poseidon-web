package edu.mum.se.poseidon.web.services;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
