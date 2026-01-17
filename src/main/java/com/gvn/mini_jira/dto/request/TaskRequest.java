package com.gvn.mini_jira.dto.request;

import java.time.LocalDateTime;

import com.gvn.mini_jira.enums.Priority;
import com.gvn.mini_jira.enums.TaskStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskRequest {

    @NotBlank(message = "Title is required")
    @Size(max = 200, message = "Title must be less than 200 characters")
    private String title;

    private String description;

    @NotNull(message = "Priority is required")
    private Priority priority;

    @NotNull(message = "Status is required")
    private TaskStatus status;

    private Long categoryId;

    private LocalDateTime dueDate;
}
