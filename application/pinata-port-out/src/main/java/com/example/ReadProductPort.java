package com.example;

import com.example.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ReadProductPort {
    Optional<Product> read(Long id);

    List<Product> readList();
}