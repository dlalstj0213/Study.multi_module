package com.example.service;

import com.example.h2.entity.Product;
import com.example.port.in.query.GetProductQuery;
import com.example.port.out.ReadProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
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