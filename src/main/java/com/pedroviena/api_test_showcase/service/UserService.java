package com.pedroviena.api_test_showcase.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pedroviena.api_test_showcase.model.User;
import com.pedroviena.api_test_showcase.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

public User findById(Long id) {
    return repository.findById(id)
            .orElseThrow(() -> new com.pedroviena.api_test_showcase.exception.ResourceNotFoundException("Usuário não encontrado com o ID: " + id));
}
    public User save(User user) {
        return repository.save(user);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    
}
