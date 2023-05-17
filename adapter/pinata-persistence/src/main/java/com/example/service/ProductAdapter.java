package com.example.service;

import com.example.ReadProductPort;
import com.example.entity.Product;
import com.example.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductAdapter implements ReadProductPort {

    private final ProductRepository productRepository;

    @Override
    public Optional<Product> read(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> readList() {
        return productRepository.findAll();
    }
}