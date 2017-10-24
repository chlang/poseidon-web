package edu.mum.se.poseidon.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Configuration
@PropertySource(value={"classpath:application-dev.properties"})
public class Config {

    @Value("${server.poseidon.url}")
    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }
}
