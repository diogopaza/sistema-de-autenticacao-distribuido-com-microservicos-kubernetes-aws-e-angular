package com.example.user_service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${spring.application.name}:${eureka.instance.instance-id}")
    private String instance;

    @GetMapping
    public ResponseEntity<String> createUser() {
        System.out.println("SERVICE-USER: " + instance);
        var response = String.format("SERVICE-USER: %s ", instance);
        return ResponseEntity.ok().body(response);
    }
}
