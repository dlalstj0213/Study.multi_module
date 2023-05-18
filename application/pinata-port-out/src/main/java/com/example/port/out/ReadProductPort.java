package com.example.port.out;

import com.example.h2.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ReadProductPort {
    Optional<Product> read(Long id);

    List<Product> readList();
}