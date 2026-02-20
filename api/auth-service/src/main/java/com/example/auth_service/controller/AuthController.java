package com.example.auth_service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${eureka.instance.instance-id}")
    private String instance;
    @GetMapping
    public ResponseEntity<String> auth() {
        System.out.println("\"AUTH-SERVICE: " + instance);
        var response = String.format("AUTH-SERVICE na porta: %s", instance);
        return ResponseEntity.ok().body(response);
    }
}
