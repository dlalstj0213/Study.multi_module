package com.example.persistence;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "factory")
@Entity
public class FactoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ownerName;

    private String phoneNumber;

    private String nation;

    private String location;

    private String employeeNo;

    private String limitedNo;
}