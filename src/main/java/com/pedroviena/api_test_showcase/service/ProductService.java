package com.pedroviena.api_test_showcase.service;

import com.pedroviena.api_test_showcase.model.Product;
import com.pedroviena.api_test_showcase.repository.ProductRepository;
import org.springframework.stereotype.Service;
import com.pedroviena.api_test_showcase.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com o ID: " + id));
    }

    public Product save(Product product) {
        return repository.save(product);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
