package com.gvn.mini_jira.repository;

import java.util.List;
import java.util.Locale.Category;

import org.apache.tomcat.util.http.parser.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gvn.mini_jira.entity.Task;
import com.gvn.mini_jira.entity.User;
import com.gvn.mini_jira.enums.TaskStatus;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByOwner(User owner);

    List<Task> findByOwnerOrderByCreatedAtDesc(User owner);

    List<Task> findByOwnerAndStatus(User owner, TaskStatus status);

    List<Task> findByOwnerAndPriority(User owner, Priority priority);

    List<Task> findByOwnerAndCategory(User owner, Category category);
}
