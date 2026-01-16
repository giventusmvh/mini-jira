package com.gvn.mini_jira.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gvn.mini_jira.service.GreetingService;
import com.gvn.mini_jira.service.HealthService;

@RestController
public class HelloController {

    private final GreetingService greetingService;
    private final HealthService healthService;

    public HelloController(GreetingService greetingService, HealthService healthService) {
        this.greetingService = greetingService;
        this.healthService = healthService;
    }

    @GetMapping("/hello")
    public String hello() {
        return greetingService.greet();
    }

    @GetMapping("/health")
    public String health() {
        return healthService.getHealth();
    }
}
