package com.gvn.mini_jira.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gvn.mini_jira.dto.request.LoginRequest;
import com.gvn.mini_jira.dto.request.UserRequest;
import com.gvn.mini_jira.dto.response.LoginResponse;
import com.gvn.mini_jira.dto.response.UserResponse;
import com.gvn.mini_jira.entity.User;
import com.gvn.mini_jira.exception.BadRequestException;
import com.gvn.mini_jira.mapper.UserMapper;
import com.gvn.mini_jira.repository.UserRepository;
import com.gvn.mini_jira.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return LoginResponse.builder()
                .token(token)
                .user(userMapper.toResponse(user))
                .build();

    }

    public UserResponse register(UserRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exist");
        }
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .active(true)
                .createdAt(LocalDateTime.now())
                .build();
        User saved = userRepository.save(user);
        return userMapper.toResponse(saved);
    }

}
