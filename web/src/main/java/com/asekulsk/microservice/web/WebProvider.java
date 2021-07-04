package com.asekulsk.microservice.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebProvider {

    public static void main(String[] args) {
        System.out.println("Hello i'm a web provider :)");
        SpringApplication.run(WebProvider.class, args);
    }
}
