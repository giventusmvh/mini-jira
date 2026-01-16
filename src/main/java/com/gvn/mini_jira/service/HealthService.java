package com.gvn.mini_jira.service;

import org.springframework.stereotype.Service;

@Service
public class HealthService {
    public String getHealth() {
        return "OK";
    }
}
