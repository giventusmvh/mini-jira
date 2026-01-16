package com.gvn.mini_jira.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    public String greet() {
        return "Hello from service layer";
    }
}
