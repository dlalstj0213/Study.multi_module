package com.example.member;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

public class MemberRequest {

    @Data
    @Builder
    @ToString
    static class SignUpRequest {
        private String email;
        private String password;
    }
}