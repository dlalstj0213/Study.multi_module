package com.example.web.online;

import com.example.h2.entity.Product;
import com.example.port.in.query.GetProductQuery;
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

    private final GetProductQuery getProductService;

    @GetMapping("")
    private ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok()
                             .body(getProductService.getProducts());
    }
}