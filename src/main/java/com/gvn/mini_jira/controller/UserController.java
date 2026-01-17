package com.gvn.mini_jira.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gvn.mini_jira.dto.request.UserRequest;
import com.gvn.mini_jira.dto.response.UserResponse;
import com.gvn.mini_jira.entity.User;
import com.gvn.mini_jira.service.UserService;
import com.gvn.mini_jira.util.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/dummy")
    public User createDummy() {
        return userService.createDummyUser();
    }

    @GetMapping("/count")
    public Long countUser() {
        return userService.countUser();
    }

    @PostMapping("/create")
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserRequest request) {

        return ApiResponse.<UserResponse>builder()
                .success(true)
                .message("User created successfully")
                .data(userService.createUser(request))
                .build();

    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getAllUsers() {
        return ApiResponse.<List<UserResponse>>builder()
                .success(true)
                .message("Users retrieved successfully")
                .data(userService.getAllUsers())
                .build();
    }

}
