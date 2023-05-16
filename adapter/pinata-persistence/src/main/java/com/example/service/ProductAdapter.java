package com.example.service;

import com.example.ItemQueryPort;
import com.example.entity.Product;
import com.example.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductAdapter implements ItemQueryPort<Product> {

    private final ProductRepository productRepository;

    @Override
    public Optional<Product> loadItem(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> loadItems() {
        return productRepository.findAll();
    }
}