package com.asekulsk.microservice.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecurityProvider {

    public static void main(String[] args) {
        System.out.println("Hello i'm a security provider :)");
        SpringApplication.run(SecurityProvider.class, args);
    }
}
