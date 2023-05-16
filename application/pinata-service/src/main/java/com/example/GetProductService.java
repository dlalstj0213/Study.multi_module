package com.example;

import com.example.model.Product;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetProductService implements GetProductQuery {

    private final ItemQueryPort<?> itemQueryPort;

    @Override
    public Product getProduct(String id) {
        itemQueryPort.loadItem(Long.parseLong(id));
        return null;
    }

    @Override
    public List<Product> getProducts() {
        return null;
    }
}