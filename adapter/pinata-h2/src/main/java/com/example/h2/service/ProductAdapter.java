package com.example.h2.service;

import com.example.port.out.ReadProductPort;
import com.example.h2.entity.Product;
import com.example.h2.repository.ProductRepository;
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