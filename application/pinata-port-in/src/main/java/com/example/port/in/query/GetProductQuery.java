package com.example.port.in.query;

import com.example.h2.entity.Product;

import java.util.List;

public interface GetProductQuery {

    Product getProduct(String id);

    List<Product> getProducts();
}