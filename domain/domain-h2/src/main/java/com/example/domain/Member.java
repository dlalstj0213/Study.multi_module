package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Member {

    private Long memberId;

    private String email;
}