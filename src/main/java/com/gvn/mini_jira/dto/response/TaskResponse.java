package com.gvn.mini_jira.dto.response;

import java.time.LocalDateTime;

import com.gvn.mini_jira.enums.Priority;
import com.gvn.mini_jira.enums.TaskStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskResponse {

    private Long id;
    private String title;
    private String description;
    private Priority priority;
    private TaskStatus status;
    private CategoryResponse category;
    private String ownerName;
    private LocalDateTime dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}