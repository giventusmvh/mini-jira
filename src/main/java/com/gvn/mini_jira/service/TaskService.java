package com.gvn.mini_jira.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.gvn.mini_jira.dto.request.TaskRequest;
import com.gvn.mini_jira.dto.response.TaskResponse;
import com.gvn.mini_jira.entity.Category;
import com.gvn.mini_jira.entity.Task;
import com.gvn.mini_jira.entity.User;
import com.gvn.mini_jira.enums.TaskStatus;
import com.gvn.mini_jira.exception.BadRequestException;
import com.gvn.mini_jira.mapper.TaskMapper;
import com.gvn.mini_jira.repository.CategoryRepository;
import com.gvn.mini_jira.repository.TaskRepository;
import com.gvn.mini_jira.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException("User not found"));
    }

    public TaskResponse createTask(TaskRequest request) {
        User owner = getCurrentUser();
        Category category = null;

        if (request.getCategoryId() != null) {
            category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new BadRequestException("Category not found"));

            // Validasi category harus milik user yg sama
            if (!category.getOwner().getId().equals(owner.getId())) {
                throw new BadRequestException("Category not found");
            }
        }

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .priority(request.getPriority())
                .status(request.getStatus())
                .category(category)
                .owner(owner)
                .dueDate(request.getDueDate())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Task saved = taskRepository.save(task);
        return taskMapper.toResponse(saved);
    }

    public List<TaskResponse> getAllTasks() {
        User owner = getCurrentUser();
        List<Task> tasks = taskRepository.findByOwnerOrderByCreatedAtDesc(owner);
        return taskMapper.toResponseList(tasks);
    }

    public TaskResponse getTaskById(Long id) {
        User owner = getCurrentUser();
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Task not found"));

        if (!task.getOwner().getId().equals(owner.getId())) {
            throw new BadRequestException("Task not found");
        }

        return taskMapper.toResponse(task);
    }

    public TaskResponse updateTask(Long id, TaskRequest request) {
        User owner = getCurrentUser();
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Task not found"));

        if (!task.getOwner().getId().equals(owner.getId())) {
            throw new BadRequestException("Task not found");
        }

        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new BadRequestException("Category not found"));

            if (!category.getOwner().getId().equals(owner.getId())) {
                throw new BadRequestException("Category not found");
            }
            task.setCategory(category);
        } else {
            task.setCategory(null);
        }

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setStatus(request.getStatus());
        task.setDueDate(request.getDueDate());
        task.setUpdatedAt(LocalDateTime.now());

        Task updated = taskRepository.save(task);
        return taskMapper.toResponse(updated);
    }

    public void deleteTask(Long id) {
        User owner = getCurrentUser();
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Task not found"));

        if (!task.getOwner().getId().equals(owner.getId())) {
            throw new BadRequestException("Task not found");
        }

        taskRepository.delete(task);
    }

    // Helper untuk update status saja (drag & drop di frontend nanti mudah)
    public TaskResponse updateStatus(Long id, TaskStatus status) {
        User owner = getCurrentUser();
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Task not found"));

        if (!task.getOwner().getId().equals(owner.getId())) {
            throw new BadRequestException("Task not found");
        }

        task.setStatus(status);
        task.setUpdatedAt(LocalDateTime.now());

        return taskMapper.toResponse(taskRepository.save(task));
    }

}