package com.example.service;

import com.example.entity.Product;
import com.example.query.GetProductQuery;
import com.example.ReadProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetProductService implements GetProductQuery {

    private final ReadProductPort readProductPort;

    @Override
    public Product getProduct(String id) {
        return readProductPort.read(Long.parseLong(id)).orElse(null);
    }

    @Override
    public List<Product> getProducts() {
        return readProductPort.readList();
    }
}