package com.example.model;

public class Product {
    String id;
    String name;
    String price;

    public record ProductId(String value) {}
}