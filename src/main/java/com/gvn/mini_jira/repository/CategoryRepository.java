package com.gvn.mini_jira.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gvn.mini_jira.entity.Category;
import com.gvn.mini_jira.entity.User;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByOwner(User owner);

    List<Category> findByOwnerOrderByCreatedAtDesc(User owner);
}
