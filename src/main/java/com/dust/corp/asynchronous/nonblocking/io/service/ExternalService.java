package com.dust.corp.asynchronous.nonblocking.io.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Represents the ExternalService class in the asynchronous-nonblocking-io-springboot project.
 *
 * @author Kashan Asim
 * @version 1.0
 * @project asynchronous-nonblocking-io-springboot
 * @module com.dust.corp.asynchronous.nonblocking.io.service
 * @class ExternalService
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
public class ExternalService {

    private final WebClient webClient;

    public ExternalService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public Mono<String> fetchData(int length) {
        return webClient.get()
                .uri("/data/{length}", length)
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(response -> System.out.println("Response: " + response))
                .doOnError(error -> System.err.println("Error: " + error.getMessage()));
    }
}

