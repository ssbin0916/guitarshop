package com.project.guitarShop.member.app;

import com.project.guitarShop.address.domain.Address;
import com.project.guitarShop.exception.ExistMemberException;
import com.project.guitarShop.exception.NotFoundMemberException;
import com.project.guitarShop.exception.ValidatePasswordException;
import com.project.guitarShop.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
//@Rollback(value = false)
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    MemberService memberService;

    @BeforeEach
    void setUp() {
        memberService = new MemberServiceImpl(memberRepository, bCryptPasswordEncoder);
    }

    @Test
    void join() {
        //given
        MemberRequest memberRequest = MemberRequest.builder()
                .loginId("loginId")
                .password("password")
                .confirmPassword("password")
                .name("name")
                .age(29)
                .phone("phone")
                .email("email")
                .role("role")
                .address(new Address("add", "re", "ss"))
                .build();

        //when
        MemberResponse memberResponse = memberService.join(memberRequest);

        //then
        assertNotNull(memberResponse);
        assertEquals("loginId", memberResponse.getLoginId());
        assertEquals("name", memberResponse.getName());
        assertEquals(29, memberResponse.getAge());
        assertEquals("phone", memberResponse.getPhone());
        assertEquals("email", memberResponse.getEmail());
        assertEquals("role", memberResponse.getRole());
    }

    @Test
    void existLoginId() {
        //given
        MemberRequest memberRequest1 = MemberRequest.builder()
                .loginId("loginId")
                .password("password")
                .confirmPassword("password")
                .name("name")
                .age(29)
                .phone("phone")
                .email("email")
                .role("role")
                .address(new Address("add", "re", "ss"))
                .build();
        memberService.join(memberRequest1);

        //when //then
        assertThrows(ExistMemberException.class, () -> {
            MemberRequest memberRequest2 = MemberRequest.builder()
                    .loginId("loginId")
                    .password("password")
                    .confirmPassword("password")
                    .name("name")
                    .age(29)
                    .phone("phone")
                    .email("email")
                    .role("role")
                    .address(new Address("add", "re", "ss"))
                    .build();
            memberService.join(memberRequest2);
        });
    }

    @Test
    void passwordNotMatch() {
        //given
        MemberRequest memberRequest = MemberRequest.builder()
                .loginId("loginId")
                .password("password")
                .confirmPassword("confirmPassword")
                .name("name")
                .age(29)
                .phone("phone")
                .email("email")
                .role("role")
                .address(new Address("add", "re", "ss"))
                .build();

        //when //then
        assertThrows(ValidatePasswordException.class, () -> memberService.join(memberRequest));
    }

    @Test
    void login() {
        //given
        MemberRequest memberRequest = MemberRequest.builder()
                .loginId("loginId")
                .password("password")
                .confirmPassword("password")
                .name("name")
                .age(29)
                .phone("phone")
                .email("email")
                .role("role")
                .address(new Address("add", "re", "ss"))
                .build();

        memberService.join(memberRequest);

        //when
        MemberResponse memberResponse = memberService.login("loginId", "password");

        //then
        assertNotNull(memberResponse);
        assertEquals("loginId", memberResponse.getLoginId());
    }

    @Test
    void updateInfo() {
        //given
        MemberRequest memberRequest = MemberRequest.builder()
                .loginId("loginId")
                .password("password")
                .confirmPassword("password")
                .name("name")
                .age(29)
                .phone("phone")
                .email("email")
                .role("role")
                .address(new Address("add", "re", "ss"))
                .build();
        memberService.join(memberRequest);

        //when
        MemberRequest updateMember = MemberRequest.builder()
                .loginId("updateLoginId")
                .name("updateName")
                .age(30)
                .phone("updatePhone")
                .email("updateEmail")
                .role("ADMIN")
                .address(new Address("add", "re", "ss"))
                .build();
        memberService.updateInfo(1L, updateMember);

        //then
        assertNotNull(updateMember);
        assertEquals("updateLoginId", updateMember.getLoginId());
        assertEquals("updateName", updateMember.getName());
        assertEquals(30, updateMember.getAge());
        assertEquals("updatePhone", updateMember.getPhone());
        assertEquals("updateEmail", updateMember.getEmail());
        assertEquals("ADMIN", updateMember.getRole());
    }

    @Test
    void updatePassword() {
        //given
        MemberRequest memberRequest = MemberRequest.builder()
                .loginId("loginId")
                .password("password")
                .confirmPassword("password")
                .name("name")
                .age(29)
                .phone("phone")
                .email("email")
                .role("role")
                .address(new Address("add", "re", "ss"))
                .build();

        memberService.join(memberRequest);

        //when
        MemberRequest updateMemberRequest = MemberRequest.builder()
                .password("updatePassword")
                .confirmPassword("updatePassword")
                .build();

        //then
        assertNotNull(updateMemberRequest);
        assertEquals("updatePassword", updateMemberRequest.getPassword());
        assertEquals("updatePassword", updateMemberRequest.getConfirmPassword());
    }

    @Test
    void delete() {
        //given
        MemberRequest memberRequest = MemberRequest.builder()
                .loginId("loginId")
                .password("password")
                .confirmPassword("password")
                .name("name")
                .age(29)
                .phone("phone")
                .email("email")
                .role("role")
                .address(new Address("add", "re", "ss"))
                .build();

        memberService.join(memberRequest);

        //when
        memberService.delete(1L);

        //then
        assertThrows(NotFoundMemberException.class, () -> {
            memberRepository.findById(1L)
                    .orElseThrow(() -> new NotFoundMemberException("존재하지 않습니다."));
        });
    }
}