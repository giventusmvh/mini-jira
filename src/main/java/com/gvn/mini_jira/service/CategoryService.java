package com.gvn.mini_jira.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.gvn.mini_jira.dto.request.CategoryRequest;
import com.gvn.mini_jira.dto.response.CategoryResponse;
import com.gvn.mini_jira.entity.Category;
import com.gvn.mini_jira.entity.User;
import com.gvn.mini_jira.exception.BadRequestException;
import com.gvn.mini_jira.mapper.CategoryMapper;
import com.gvn.mini_jira.repository.CategoryRepository;
import com.gvn.mini_jira.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final CategoryMapper categoryMapper;

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).orElseThrow(() -> new BadRequestException("User not found"));
    }

    public CategoryResponse createCategory(CategoryRequest request) {
        User owner = getCurrentUser();

        Category category = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .color(request.getColor())
                .owner(owner)
                .createdAt(LocalDateTime.now())
                .build();

        Category saved = categoryRepository.save(category);
        return categoryMapper.toResponse(saved);
    }

    public List<CategoryResponse> getAllCategories() {
        User owner = getCurrentUser();
        List<Category> categories = categoryRepository.findByOwnerOrderByCreatedAtDesc(owner);
        return categoryMapper.toResponseList(categories);
    }

    public CategoryResponse getCategoryByid(Long id) {
        User owner = getCurrentUser();
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Category not found"));

        if (!category.getOwner().getId().equals(owner.getId())) {
            throw new BadRequestException("You are not the owner of this category");

        }

        return categoryMapper.toResponse(category);
    }

    public CategoryResponse updateCategory(Long id, CategoryRequest request) {
        User owner = getCurrentUser();
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Category not found"));

        if (!category.getOwner().getId().equals(owner.getId())) {
            throw new BadRequestException("You are not the owner of this Category");
        }

        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setColor(request.getColor());

        Category updated = categoryRepository.save(category);
        return categoryMapper.toResponse(updated);

    }

    public void deleteCategory(Long id) {
        User owner = getCurrentUser();
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Category not found"));

        if (!category.getOwner().getId().equals(owner.getId())) {
            throw new BadRequestException("You are not the owner of this Category");
        }

        categoryRepository.delete(category);
    }

    // private Category checkCategory(Long id) {
    // User owner = getCurrentUser();
    // Category category = categoryRepository.findById(id)
    // .orElseThrow(() -> new BadRequestException("Category not found"));

    // if (!category.getOwner().getId().equals(owner.getId())) {
    // throw new BadRequestException("You are not the owner of this Category");
    // }

    // return category;
    // }

}
