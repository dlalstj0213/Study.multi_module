package com.example.pj.online_store.adapter.out.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "GOODS")
public class GoodsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String modelId;

    @Column
    private String name;

    private BigInteger price;
}