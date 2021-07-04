package com.asekulsk.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class APIProvider {

    public static void main(String[] args) {
        SpringApplication.run(APIProvider.class, args);
    }
}
