package com.gvn.mini_jira.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gvn.mini_jira.dto.request.LoginRequest;
import com.gvn.mini_jira.dto.response.LoginResponse;
import com.gvn.mini_jira.service.AuthService;
import com.gvn.mini_jira.util.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        return ApiResponse.<LoginResponse>builder()
                .success(true)
                .message("Login successfully")
                .data(authService.login(request))
                .build();
    }

}
