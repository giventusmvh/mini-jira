package com.gvn.mini_jira.util;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse<T> {

    private boolean success;
    private T data;
    private String message;

    private Map<String, String> errors;

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .data(data)
                .build();
    }

    public static ApiResponse<?> error(String message) {
        return ApiResponse.builder()
                .success(false)
                .message(message)
                .build();
    }

    // ✅ NEW
    public static ApiResponse<?> validationError(Map<String, String> errors) {
        return ApiResponse.builder()
                .success(false)
                .message("Validation failed")
                .errors(errors)
                .build();
    }

}
