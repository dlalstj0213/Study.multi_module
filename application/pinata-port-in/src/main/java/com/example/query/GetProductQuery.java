package com.example.query;

import com.example.entity.Product;

import java.util.List;

public interface GetProductQuery {

    Product getProduct(String id);

    List<Product> getProducts();
}