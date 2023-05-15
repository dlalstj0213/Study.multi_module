package com.example.repository;

import com.example.persistence.MemberEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@DisplayName("MemberRepository 테스트")
@ExtendWith(SpringExtension.class)
@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    MemberEntity createMember() {
        final String EMAIL = "dlalstj0213@gmail.com";
        final String PASSWORD = "1234";
        return memberRepository.save(new MemberEntity(null, EMAIL, PASSWORD));
    }

    @DisplayName("Member 생성")
    @Test
    void createMemberTest() {
        final String EMAIL = "dlalstj0213@gmail.com";
        final String PASSWORD = "1234";
        MemberEntity memberEntity = memberRepository.save(new MemberEntity(null, EMAIL, PASSWORD));
        assertEquals(memberEntity.getEmail(), EMAIL);
        assertEquals(memberEntity.getPassword(), PASSWORD);
    }

    @DisplayName("Member 생성 및 조회")
    @Test
    void findByIdTest() {
        MemberEntity memberEntity = createMember();
        Optional<MemberEntity> result = memberRepository.findById(memberEntity.getId());
        assertTrue("findById() exist", result.isPresent());
    }

    @DisplayName("Member 수정")
    @Test
    void updateTest() {
        final String EMAIL = "dlalstj5213@gmail.com";
        final String PASSWORD = "4321";
        MemberEntity memberEntity = createMember();
        memberEntity.setEmail(EMAIL);
        memberEntity.setPassword(PASSWORD);
        MemberEntity updatedMemberEntity = memberRepository.findById(memberEntity.getId()).orElseGet(MemberEntity::new);
        assertEquals(memberEntity.getEmail(), updatedMemberEntity.getEmail());
        assertEquals(memberEntity.getPassword(), updatedMemberEntity.getPassword());
    }

    @DisplayName("Member 삭제")
    @Test
    void deleteTest() {
        MemberEntity memberEntity = createMember();
        MemberEntity foundMemberEntity = memberRepository.findById(memberEntity.getId()).orElseGet(MemberEntity::new);
        memberRepository.deleteById(foundMemberEntity.getId());
        assertEquals(memberRepository.findAll().size(), 0);
    }
}