package com.example.club.sportif.controller.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class TestApiController {

    @GetMapping("/test")
    public String test() {
        return "API is working!";
    }
    
    @GetMapping
    public String apiInfo() {
        return "Club Sportif API - Available endpoints: /api/activites, /api/membres, /api/inscriptions";
    }
}
