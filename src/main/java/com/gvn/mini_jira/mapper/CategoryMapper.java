package com.gvn.mini_jira.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.gvn.mini_jira.dto.response.CategoryResponse;
import com.gvn.mini_jira.entity.Category;

@Component
public class CategoryMapper {
    public CategoryResponse toResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .color(category.getColor())
                .createdAt(category.getCreatedAt())
                .build();
    }

    public List<CategoryResponse> toResponseList(List<Category> categories) {
        return categories.stream()
                .map(this::toResponse)
                .toList();
    }
}
