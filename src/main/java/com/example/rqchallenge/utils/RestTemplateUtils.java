package com.example.rqchallenge.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateUtils {

    @Autowired
    private RestTemplate restTemplate;

    public String get(String apiUrl) {
        return restTemplate.getForObject(apiUrl, String.class);
    }

    public String post(String apiUrl, String requestPayload) {
        return restTemplate.postForObject(apiUrl, requestPayload, String.class);
    }
    public void delete(String apiUrl) {
        restTemplate.delete(apiUrl);
    }

}
