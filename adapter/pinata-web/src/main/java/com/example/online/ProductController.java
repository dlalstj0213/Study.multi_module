package com.example.online;

import com.example.GetProductQuery;
import com.example.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/online/product")
@RestController
public class ProductController {

    private final GetProductQuery productQuery;

    @GetMapping("")
    private ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok()
                             .body(productQuery.getProducts());
    }
}