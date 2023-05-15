package com.example.persistence;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
@Entity
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelId;

    private String factoryId;

    private String serialNumber;

    private LocalDateTime prodTime;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}