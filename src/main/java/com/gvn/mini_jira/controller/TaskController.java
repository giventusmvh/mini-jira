package com.gvn.mini_jira.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.gvn.mini_jira.dto.request.TaskRequest;
import com.gvn.mini_jira.dto.response.TaskResponse;
import com.gvn.mini_jira.enums.TaskStatus;
import com.gvn.mini_jira.service.TaskService;
import com.gvn.mini_jira.util.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ApiResponse<TaskResponse> createTask(@RequestBody @Valid TaskRequest request) {
        return ApiResponse.success(taskService.createTask(request), "Task created successfully");
    }

    @GetMapping
    public ApiResponse<List<TaskResponse>> getAllTasks() {
        return ApiResponse.success(taskService.getAllTasks(), "Tasks retrieved successfully");
    }

    @GetMapping("/{id}")
    public ApiResponse<TaskResponse> getTaskById(@PathVariable Long id) {
        return ApiResponse.success(taskService.getTaskById(id), "Task retrieved successfully");
    }

    @PutMapping("/{id}")
    public ApiResponse<TaskResponse> updateTask(@PathVariable Long id, @RequestBody @Valid TaskRequest request) {
        return ApiResponse.success(taskService.updateTask(id, request), "Task updated successfully");
    }

    @PatchMapping("/{id}/status")
    public ApiResponse<TaskResponse> updateStatus(@PathVariable Long id, @RequestParam TaskStatus status) {
        return ApiResponse.success(taskService.updateStatus(id, status), "Task status updated successfully");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ApiResponse.success(null, "Task deleted successfully");
    }
}