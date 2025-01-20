package com.dust.corp.asynchronous.nonblocking.io.service;

import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

/**
 * Represents the CustomHttpClient class in the asynchronous-nonblocking-io-springboot project.
 *
 * @author Kashan Asim
 * @version 1.0
 * @project asynchronous-nonblocking-io-springboot
 * @module com.dust.corp.asynchronous.nonblocking.io.service
 * @class CustomHttpClient
 * @lastModifiedBy Kashan.Asim
 * @lastModifiedDate 1/17/2025
 * @license Licensed under the Apache License, Version 2.0
 * @description A brief description of the class functionality.
 * @notes <ul>
 * <li>Provide any additional notes or remarks here.</li>
 * </ul>
 * @since 1/17/2025
 */
@Slf4j
@Component
public class CustomHttpClient {
    // Create a single OkHttpClient instance for reuse
    /*private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(Duration.ofSeconds(10)) // Set connection timeout
            .readTimeout(Duration.ofSeconds(30))    // Set read timeout
            .writeTimeout(Duration.ofSeconds(30))   // Set write timeout
            .build();*/

    /*private static volatile ConnectionPool connectionPool = new ConnectionPool(5, 5, TimeUnit.MINUTES);
    private static volatile OkHttpClient client = new OkHttpClient.Builder()
            .connectionPool(connectionPool)
            .build();*/

    @Autowired
    private volatile OkHttpClient client;

    public static void main(String[] args) {
        String url = "http://localhost:9010/external-service/api/buy/air/self";

        String jsonBody = "{\"title\": \"foo\", \"body\": \"bar\", \"userId\": 1}";

        CustomHttpClient optimizedHttpClient = new CustomHttpClient();

        for (int i = 0; i < 10; i++) {
            optimizedHttpClient.sendPostRequest(url, jsonBody);
        }
    }

    public Mono<String> fetchData(int length) {
        // This blocks the calling thread
        return sendGetRequest("http://localhost:8080/data/"+length, "String.class, length");
    }

    public Mono<String> sendGetRequest(String url, String jsonBody)  {
//        // Create the request body
//        RequestBody body = RequestBody.create(jsonBody, MediaType.get("application/json; charset=utf-8"));

        // Build the HTTP request
        Request request = new Request.Builder()
                .url(url)
                .header("Connection", "keep-alive") // Explicit Keep-Alive header
                .header("Content-Type", "application/json")
//                .header("Authorization", "Basic c3VwZXJhZG1pbjpzdXBlcmFkbWlu")
                .get()
//                .post(body)
                .build();

        // Measure the response time
        Instant startTime = Instant.now();

        // Send the request and get the response
        try (Response response = client.newCall(request).execute()) {
            Instant endTime = Instant.now();
            long duration = Duration.between(startTime, endTime).toMillis();

            // Print the response details
            log.info("Response received in: " + duration + " ms");

            return Mono.just(response.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String sendPostRequest(String url, String jsonBody)  {
        // Create the request body
        RequestBody body = RequestBody.create(jsonBody, MediaType.get("application/json; charset=utf-8"));

        // Build the HTTP request
        Request request = new Request.Builder()
                .url(url)
                .header("Connection", "keep-alive") // Explicit Keep-Alive header
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic c3VwZXJhZG1pbjpzdXBlcmFkbWlu")
                .post(body)
                .build();

        // Measure the response time
        Instant startTime = Instant.now();

        // Send the request and get the response
        try (Response response = client.newCall(request).execute()) {
            Instant endTime = Instant.now();
            long duration = Duration.between(startTime, endTime).toMillis();

            // Print the response details
            log.info("Response received in: " + duration + " ms");

            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
