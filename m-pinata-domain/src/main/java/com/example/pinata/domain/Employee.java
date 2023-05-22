package com.example.pinata.domain;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private Long id;

    private String email;

    private String name;

    private String sex;
}