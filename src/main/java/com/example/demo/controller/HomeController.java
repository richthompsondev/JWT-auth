package com.example.demo.controller;
// From Udemy's https://www.udemy.com/course/practical-bootcamp-jwt-authentication-with-java-springboot/

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from HomeController";
    }
}
