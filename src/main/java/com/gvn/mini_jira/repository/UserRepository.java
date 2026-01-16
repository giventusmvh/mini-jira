package com.gvn.mini_jira.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gvn.mini_jira.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
