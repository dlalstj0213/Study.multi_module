package com.example.service;

import com.example.domain.Member;
import com.example.persistence.MemberEntity;
import com.example.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberQueryService {

    private final MemberRepository memberRepository;

    public Member signUp(MemberCommand command) {
        MemberEntity memberEntity = memberRepository.save(MemberEntity.builder()
                                          .email(command.getEmail())
                                          .password(command.getPassword())
                                          .build());
        return new Member(memberEntity.getId(), memberEntity.getEmail());
    }
}