package com.example.member;

import com.example.domain.Member;
import com.example.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final String EMAIL = "tester@gmail.com";
    private final String PASSWORD = "password";

    private final MemberRepository userRepository;

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp() {
        var user = userRepository.save(Member.builder().email(EMAIL).password(PASSWORD).build());
        return ResponseEntity.ok().body(user);
    }
}
