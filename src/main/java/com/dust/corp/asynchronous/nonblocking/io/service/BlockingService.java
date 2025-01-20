package com.dust.corp.asynchronous.nonblocking.io.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Represents the BlockingService class in the asynchronous-nonblocking-io-springboot project.
 *
 * @author Kashan Asim
 * @version 1.0
 * @project asynchronous-nonblocking-io-springboot
 * @module com.dust.corp.asynchronous.nonblocking.io.service
 * @class BlockingService
 * @lastModifiedBy Kashan.Asim
 * @lastModifiedDate 1/16/2025
 * @license Licensed under the Apache License, Version 2.0
 * @description A brief description of the class functionality.
 * @notes <ul>
 * <li>Provide any additional notes or remarks here.</li>
 * </ul>
 * @since 1/16/2025
 */
@Service
public class BlockingService {

    private final RestTemplate restTemplate;

    @Autowired
    public BlockingService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String fetchData(int length) {
        // This blocks the calling thread
        return restTemplate.getForObject("http://localhost:8080/data/{length}", String.class, length);
    }
}
