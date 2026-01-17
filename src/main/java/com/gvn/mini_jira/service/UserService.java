package com.gvn.mini_jira.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gvn.mini_jira.dto.request.UserRequest;
import com.gvn.mini_jira.dto.response.UserResponse;
import com.gvn.mini_jira.entity.User;
import com.gvn.mini_jira.exception.BadRequestException;
import com.gvn.mini_jira.mapper.UserMapper;
import com.gvn.mini_jira.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public User createDummyUser() {
        User user = User.builder()
                .name("Wei Sheng")
                .email("wei.sheng@tutorle.com")
                .password(passwordEncoder.encode("password"))
                .active(true)
                .createdAt(LocalDateTime.now())
                .build();

        return userRepository.save(user);
    }

    public Long countUser() {
        return userRepository.count();
    }

    public UserResponse createUser(UserRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .active(true)
                .createdAt(LocalDateTime.now())
                .build();

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exist");
        }

        User saved = userRepository.save(user);

        return userMapper.toResponse(saved);

    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();

        return userMapper.toResponseList(users);
    }

}
