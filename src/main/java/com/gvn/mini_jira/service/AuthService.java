package com.gvn.mini_jira.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gvn.mini_jira.dto.request.LoginRequest;
import com.gvn.mini_jira.dto.response.LoginResponse;
import com.gvn.mini_jira.entity.User;
import com.gvn.mini_jira.exception.BadRequestException;
import com.gvn.mini_jira.repository.UserRepository;
import com.gvn.mini_jira.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return LoginResponse.builder()
                .token(token)
                .build();

    }

}
