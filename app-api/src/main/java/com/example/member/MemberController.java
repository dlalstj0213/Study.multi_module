package com.example.member;

import com.example.domain.Member;
import com.example.service.MemberCommand;
import com.example.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/member")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberQueryService memberQueryService;

    @PostMapping("/sign-up")
    private ResponseEntity<Member> signUp(@RequestBody MemberRequest.SignUpRequest signUpRequest) {
        log.info("signUpRequest = {}", signUpRequest);

        Member member = memberQueryService.signUp(MemberCommand.builder()
                                                               .email(signUpRequest.getEmail())
                                                               .password(signUpRequest.getPassword())
                                                               .build());
        return ResponseEntity.ok()
                             .body(member);
    }
}