package com.gvn.mini_jira.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.gvn.mini_jira.dto.response.TaskResponse;
import com.gvn.mini_jira.entity.Task;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TaskMapper {

    private final CategoryMapper categoryMapper;

    public TaskResponse toResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .priority(task.getPriority())
                .status(task.getStatus())
                .category(task.getCategory() != null
                        ? categoryMapper.toResponse(task.getCategory())
                        : null)
                .ownerName(task.getOwner().getName())
                .dueDate(task.getDueDate())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .build();
    }

    public List<TaskResponse> toResponseList(List<Task> tasks) {
        return tasks.stream()
                .map(this::toResponse)
                .toList();
    }

}