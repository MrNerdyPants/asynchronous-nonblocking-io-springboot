package com.dust.corp.asynchronous.nonblocking.io.util;

import java.security.SecureRandom;

/**
 * Represents the RandomStringGeneratorUtility class in the asynchronous-nonblocking-io-springboot project.
 *
 * @author Kashan Asim
 * @version 1.0
 * @project asynchronous-nonblocking-io-springboot
 * @module com.dust.corp.asynchronous.nonblocking.io.util
 * @class RandomStringGeneratorUtility
 * @lastModifiedBy Kashan.Asim
 * @lastModifiedDate 1/16/2025
 * @license Licensed under the Apache License, Version 2.0
 * @description A brief description of the class functionality.
 * @notes <ul>
 * <li>Provide any additional notes or remarks here.</li>
 * </ul>
 * @since 1/16/2025
 */
public class UtilityService {

    private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateRandomString(int length) {
        if (length < 1) throw new IllegalArgumentException("Length must be greater than 0");
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = RANDOM.nextInt(CHAR_POOL.length());
            stringBuilder.append(CHAR_POOL.charAt(randomIndex));
        }
        return stringBuilder.toString();
    }

    public static void countToAnyNumber(long number){
        for (int i = 0; i < number; i++) {
            if(i+1==number){
                System.out.println("Count Completed!!");
            }
        }
    }

    public static void main(String[] args) {
        int desiredLength = 16; // Replace with your desired length
        String randomString = generateRandomString(desiredLength);
        System.out.println("Generated Random String: " + randomString);
    }
}

