package com.gvn.mini_jira.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.gvn.mini_jira.dto.response.UserResponse;
import com.gvn.mini_jira.entity.User;

@Component
public class UserMapper {

    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .active(user.getActive())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public List<UserResponse> toResponseList(List<User> users) {
        return users.stream()
                .map(this::toResponse)
                .toList();
    }
}
