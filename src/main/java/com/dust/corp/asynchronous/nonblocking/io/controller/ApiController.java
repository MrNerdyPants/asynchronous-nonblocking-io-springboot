package com.dust.corp.asynchronous.nonblocking.io.controller;

import com.dust.corp.asynchronous.nonblocking.io.service.BlockingService;
import com.dust.corp.asynchronous.nonblocking.io.service.CustomHttpClient;
import com.dust.corp.asynchronous.nonblocking.io.service.ExternalService;
import com.dust.corp.asynchronous.nonblocking.io.util.UtilityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Represents the ApiController class in the asynchronous-nonblocking-io-springboot project.
 *
 * @author Kashan Asim
 * @version 1.0
 * @project asynchronous-nonblocking-io-springboot
 * @module com.dust.corp.asynchronous.nonblocking.io.controller
 * @class ApiController
 * @lastModifiedBy Kashan.Asim
 * @lastModifiedDate 1/16/2025
 * @license Licensed under the Apache License, Version 2.0
 * @description A brief description of the class functionality.
 * @notes <ul>
 * <li>Provide any additional notes or remarks here.</li>
 * </ul>
 * @since 1/16/2025
 */

@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {

    private final ExternalService externalService;

    private final BlockingService blockingService;

    private final CustomHttpClient customHttpClient;

    @Autowired
    public ApiController(ExternalService externalService, BlockingService blockingService, CustomHttpClient customHttpClient) {
        this.externalService = externalService;
        this.blockingService = blockingService;
        this.customHttpClient = customHttpClient;
    }

    @GetMapping("/fetch/{length}")
    public Mono<String> fetchData(@PathVariable int length) throws InterruptedException {

        Mono<String> result = externalService.fetchData(length)
                .map(response -> "Processed Response: " + response);


        log.info("starting count!");
        UtilityService.countToAnyNumber(1000000000l);
        log.info("count completed!!");

        return result;
    }

    @GetMapping("/fetch/block/{length}")
    public String fetchDataFromBlockingService(@PathVariable int length) throws InterruptedException {

        String result = "Processed Response: " + blockingService.fetchData(length);

        log.info("starting count!");
        UtilityService.countToAnyNumber(1000000000l);
        log.info("count completed!!");

        return result;
    }

    @GetMapping("/fetch/custom/{length}")
    public String fetchDataFromCustomClient(@PathVariable int length) throws InterruptedException {

        String result = "Processed Response: " + customHttpClient.fetchData(length);

        log.info("starting count!");
        UtilityService.countToAnyNumber(1000000000l);
        log.info("count completed!!");

        return result;
    }
}

