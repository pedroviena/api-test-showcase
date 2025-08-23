package com.pedroviena.api_test_showcase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedroviena.api_test_showcase.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
