package com.example.h2.repository;

import com.example.h2.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigInteger;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ProductRepository 테스트")
@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveTest() {
        String name = "상품1";
        Product saved = productRepository.save(Product.builder()
                                                      .name(name)
                                                      .price(new BigInteger("10000"))
                                                      .updatedAt(LocalDateTime.now())
                                                      .createdAt(LocalDateTime.now())
                                                      .build());
        assertEquals(name, saved.getName());
    }
}