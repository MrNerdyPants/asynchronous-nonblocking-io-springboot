package com.dust.corp.asynchronous.nonblocking.io.config;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Represents the HttpClientConfiguration class in the asynchronous-nonblocking-io-springboot project.
 *
 * @author Kashan Asim
 * @version 1.0
 * @project asynchronous-nonblocking-io-springboot
 * @module com.dust.corp.asynchronous.nonblocking.io.config
 * @class HttpClientConfiguration
 * @lastModifiedBy Kashan.Asim
 * @lastModifiedDate 1/17/2025
 * @license Licensed under the Apache License, Version 2.0
 * @description A brief description of the class functionality.
 * @notes <ul>
 * <li>Provide any additional notes or remarks here.</li>
 * </ul>
 * @since 1/17/2025
 */
@Configuration
public class HttpClientConfiguration {
    @Bean
    public ConnectionPool connectionPool() {
        return new ConnectionPool(5, 5, TimeUnit.MINUTES);
    }

    @Bean
    public OkHttpClient client() {
        return new OkHttpClient.Builder()
                .connectionPool(connectionPool())
                .build();
    }
}