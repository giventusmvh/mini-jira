package com.gvn.mini_jira.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.gvn.mini_jira.dto.request.CategoryRequest;
import com.gvn.mini_jira.dto.response.CategoryResponse;
import com.gvn.mini_jira.service.CategoryService;
import com.gvn.mini_jira.util.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ApiResponse<CategoryResponse> createCategory(@RequestBody @Valid CategoryRequest request) {
        return ApiResponse.success(categoryService.createCategory(request), "Category created successfully");
    }

    @GetMapping
    public ApiResponse<List<CategoryResponse>> getAllCategories() {
        return ApiResponse.success(categoryService.getAllCategories(), "Categories retrieved successfully");
    }

    @GetMapping("/{id}")
    public ApiResponse<CategoryResponse> getCategoryById(@PathVariable Long id) {
        return ApiResponse.success(categoryService.getCategoryById(id), "Category retrieved successfully");
    }

    @PutMapping("/{id}")
    public ApiResponse<CategoryResponse> updateCategory(@PathVariable Long id,
            @RequestBody @Valid CategoryRequest request) {
        return ApiResponse.success(categoryService.updateCategory(id, request), "Category updated successfully");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ApiResponse.success(null, "Category deleted successfully");
    }
}