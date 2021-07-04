package com.asekulsk.microservice.web;

import com.vaadin.spring.annotation.EnableVaadin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableVaadin
@SpringBootApplication
@EnableEurekaClient
public class WebProvider {

    public static void main(String[] args) {
        SpringApplication.run(WebProvider.class, args);
    }
}
