package com.hills.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class ServerConfiguration {

    @Bean
    public RestTemplate serverRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(
                new DefaultUriBuilderFactory("https://30hills.com/api/"));
        return restTemplate;
    }
}
