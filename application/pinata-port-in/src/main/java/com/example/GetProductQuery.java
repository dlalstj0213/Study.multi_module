package com.example;

import com.example.model.Product;

import java.util.List;

public interface GetProductQuery {

    Product getProduct(String id);

    List<Product> getProducts();
}