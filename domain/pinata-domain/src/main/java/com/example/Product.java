package com.example;

import lombok.Value;

@Value
public class Product {

    private final String id;

    private final Money price;

    private final Money discount;
}