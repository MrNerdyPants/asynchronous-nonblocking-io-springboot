package com.dust.corp.asynchronous.nonblocking.io.controller;

import com.dust.corp.asynchronous.nonblocking.io.util.UtilityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Represents the MockController class in the asynchronous-nonblocking-io-springboot project.
 *
 * @author Kashan Asim
 * @version 1.0
 * @project asynchronous-nonblocking-io-springboot
 * @module com.dust.corp.asynchronous.nonblocking.io.controller
 * @class MockController
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
public class MockController {

    @GetMapping(path = "/data/{length}")
    private String getRandomDataWithDelay(@PathVariable int length) throws InterruptedException {
        int sleepTime = (5) * 1000;
//                (new Random().nextInt(5)) * 1000;
        log.info("Putting Thread to sleep for :" + sleepTime + "ms");
        Thread.sleep(sleepTime);

        return UtilityService.generateRandomString(length);
    }
}
