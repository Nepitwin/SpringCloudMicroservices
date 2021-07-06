package com.asekulsk.microservice.web;

import com.asekulsk.microservice.web.spring.security.model.Role;
import com.asekulsk.microservice.web.spring.security.model.User;
import com.asekulsk.microservice.web.spring.security.repository.UserRepository;
import com.asekulsk.microservice.web.spring.security.type.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collections;

@EnableEurekaClient
@SpringBootApplication
public class WebProvider implements CommandLineRunner {

    /**
     * User repository to create mockup user data.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Password encoder to encode raw passwords as hash value.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(WebProvider.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(RoleType.values()).forEach(x -> {

            // Obtain roles for example ROLE_ADMIN split underscore
            // and take second value "ADMIN" and make value lower case "admin"
            String name = x.name().split("_")[1].toLowerCase();

            User user = new User();
            user.setUsername(name);
            user.setPassword(passwordEncoder.encode("1234"));

            Role role = new Role();
            role.setType(x);
            role.setUser(user);

            user.setRoles(Collections.singletonList(role));
            userRepository.save(user);

        });
    }
}
