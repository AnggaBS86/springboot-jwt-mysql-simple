package com.angga.springbootjwtmysqlsimple.api.services;

import org.springframework.web.client.RestTemplate;

public class MyHttpRequestService {

    public static String getResponseFromUrl(String url) {
        var restTemplate = new RestTemplate();
        var response = restTemplate.getForEntity(url, String.class);
        var jsonValue = response.getBody();
        return jsonValue;
    }

}
