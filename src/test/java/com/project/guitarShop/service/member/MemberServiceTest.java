package com.project.guitarShop.service.member;

import com.project.guitarShop.domain.address.Address;
import com.project.guitarShop.domain.member.Member;
import com.project.guitarShop.dto.member.MemberRequest;
import com.project.guitarShop.dto.member.MemberRequest.*;
import com.project.guitarShop.dto.member.MemberResponse.*;
import com.project.guitarShop.exception.ExistMemberException;
import com.project.guitarShop.exception.NotFoundMemberException;
import com.project.guitarShop.exception.ValidatePasswordException;
import com.project.guitarShop.repository.member.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
//@Rollback(value = false)
class MemberServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    MemberService memberService;

    @BeforeEach
    void setUp() {
        memberService = new MemberService(memberRepository, bCryptPasswordEncoder);
    }

    @Test
    void join() {
        //given
        JoinRequest request = MemberRequest.JoinRequest.builder()
                .loginId("loginId")
                .password("password")
                .confirmPassword("password")
                .name("name")
                .rrn("rrn")
                .gender("gender")
                .phone("phone")
                .email("email")
                .address(new Address("add", "re", "ss"))
                .build();

        //when
        JoinResponse response = memberService.join(request);

        //then
        assertNotNull(response);
        assertEquals("loginId", response.getLoginId());
        assertEquals("name", response.getName());
        assertEquals("email", response.getEmail());
    }

    @Test
    void existLoginId() {
        //given
        JoinRequest request1 = MemberRequest.JoinRequest.builder()
                .loginId("loginId")
                .password("password")
                .confirmPassword("password")
                .name("name")
                .rrn("rrn")
                .gender("gender")
                .phone("phone")
                .email("email")
                .address(new Address("add", "re", "ss"))
                .build();
        memberService.join(request1);

        //when
        JoinRequest request2 = MemberRequest.JoinRequest.builder()
                .loginId("loginId")
                .password("password")
                .confirmPassword("password")
                .name("name")
                .rrn("rrn")
                .gender("gender")
                .phone("phone")
                .email("email")
                .address(new Address("add", "re", "ss"))
                .build();

        //then
        assertThrows(ExistMemberException.class, () -> memberService.join(request2));
    }

    @Test
    void passwordNotMatch() {
        JoinRequest request = MemberRequest.JoinRequest.builder()
                .loginId("loginId")
                .password("password")
                .confirmPassword("notMatchPassword")
                .name("name")
                .rrn("rrn")
                .gender("gender")
                .phone("phone")
                .email("email")
                .address(new Address("add", "re", "ss"))
                .build();

        //when //then
        assertThrows(ValidatePasswordException.class, () -> memberService.join(request));
    }

    @Test
    void login() {
        JoinRequest request = MemberRequest.JoinRequest.builder()
                .loginId("loginId")
                .password("password")
                .confirmPassword("password")
                .name("name")
                .rrn("rrn")
                .gender("gender")
                .phone("phone")
                .email("email")
                .address(new Address("add", "re", "ss"))
                .build();

        memberService.join(request);

        //when
        LoginResponse response = memberService.login("loginId", "password");

        //then
        assertNotNull(response);
        assertEquals("loginId", response.getLoginId());
    }

    @Test
    void updateInfo() {
        JoinRequest request = MemberRequest.JoinRequest.builder()
                .loginId("loginId")
                .password("password")
                .confirmPassword("password")
                .name("name")
                .rrn("rrn")
                .gender("gender")
                .phone("phone")
                .email("email")
                .address(new Address("add", "re", "ss"))
                .build();

        memberService.join(request);

        //when
        UpdateInfoRequest updateMember = UpdateInfoRequest.builder()
                .phone("updatePhone")
                .email("updateEmail")
                .address(new Address("up", "da", "te"))
                .build();

        memberService.updateInfo(1L, updateMember);

        //then
        assertNotNull(updateMember);
        assertEquals("updatePhone", updateMember.getPhone());
        assertEquals("updateEmail", updateMember.getEmail());
    }

    @Test
    void updatePassword() {
        JoinRequest request = MemberRequest.JoinRequest.builder()
                .loginId("loginId")
                .password("password")
                .confirmPassword("password")
                .name("name")
                .rrn("rrn")
                .gender("gender")
                .phone("phone")
                .email("email")
                .address(new Address("add", "re", "ss"))
                .build();

        JoinResponse member = memberService.join(request);

        //when
        UpdatePasswordRequest updatePasswordRequest = UpdatePasswordRequest.builder()
                .password("updatePassword")
                .confirmPassword("updatePassword")
                .build();

        memberService.updatePassword(member.getId(), updatePasswordRequest, bCryptPasswordEncoder);


        //then
        Member updatedMember = memberRepository.findById(member.getId()).orElseThrow(() ->
                new NotFoundMemberException("찾을 수 없는 회원입니다."));

        assertTrue(bCryptPasswordEncoder.matches("updatePassword", updatedMember.getPassword()));
        assertTrue(bCryptPasswordEncoder.matches("updatePassword", updatedMember.getConfirmPassword()));
    }

    @Test
    void delete() {
        JoinRequest request = MemberRequest.JoinRequest.builder()
                .loginId("loginId")
                .password("password")
                .confirmPassword("password")
                .name("name")
                .rrn("rrn")
                .gender("gender")
                .phone("phone")
                .email("email")
                .address(new Address("add", "re", "ss"))
                .build();

        memberService.join(request);

        //when
        memberService.delete(1L);

        //then
        assertThrows(NotFoundMemberException.class, () -> {
            memberRepository.findById(1L)
                    .orElseThrow(() -> new NotFoundMemberException("존재하지 않습니다."));
        });
    }
}