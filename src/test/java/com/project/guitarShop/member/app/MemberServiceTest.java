package com.project.guitarShop.member.app;

import com.project.guitarShop.domain.address.Address;
import com.project.guitarShop.exception.ExistMemberException;
import com.project.guitarShop.exception.NotFoundMemberException;
import com.project.guitarShop.exception.ValidatePasswordException;
import com.project.guitarShop.member.repository.MemberRepository;
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
        MemberRequest memberRequest = new MemberRequest(
                "loginId",
                "password",
                "password",
                "name",
                29,
                "phone",
                "email",
                "ROLE_USER",
                new Address("addr1", "addr2", "addr3"),
                null
        );

        //when
        MemberResponse memberResponse = memberService.join(memberRequest);

        //then
        assertNotNull(memberResponse);
        assertEquals("loginId", memberResponse.getLoginId());
        assertNotEquals("password", memberResponse.getPassword());
    }

    @Test
    void existLoginId() {
        //given
        MemberRequest memberRequest1 = new MemberRequest(
                "loginId",
                "password",
                "password",
                "name",
                29,
                "phone",
                "email",
                "ROLE_USER",
                new Address("addr1", "addr2", "addr3"),
                null
        );

        memberService.join(memberRequest1);

        MemberRequest memberRequest2 = new MemberRequest(
                "loginId",
                "password",
                "password",
                "name",
                29,
                "phone",
                "email",
                "ROLE_USER",
                new Address("addr1", "addr2", "addr3"),
                null
        );

        //when //then
        assertThrows(ExistMemberException.class, () -> memberService.join(memberRequest2));
    }

    @Test
    void passwordNotMatch() {
        //given
        MemberRequest memberRequest = new MemberRequest(
                "loginId",
                "password",
                "confirmPassword",
                "name",
                29,
                "phone",
                "email",
                "ROLE_USER",
                new Address("addr1", "addr2", "addr3"),
                null
        );

        //when //then
        assertThrows(ValidatePasswordException.class, () -> memberService.join(memberRequest));
    }

    @Test
    void login() {
        //given
        MemberRequest memberRequest = new MemberRequest(
                "loginId",
                "password",
                "password",
                "name",
                29,
                "phone",
                "email",
                "ROLE_USER",
                new Address("addr1", "addr2", "addr3"),
                null
        );

        memberService.join(memberRequest);

        //when
        MemberResponse memberResponse = memberService.login("loginId", "password");

        //then
        assertNotNull(memberResponse);
        assertEquals("loginId", memberResponse.getLoginId());
        assertEquals("password", memberResponse.getPassword());

    }

    @Test
    void updateInfo() {
        //given
        MemberRequest memberRequest = new MemberRequest(
                "loginId",
                "password",
                "password",
                "name",
                29,
                "phone",
                "email",
                "ROLE_USER",
                new Address("addr1", "addr2", "addr3"),
                null
        );

        memberService.join(memberRequest);

        //when
        MemberResponse memberResponse = MemberResponse.builder()
                .phone("updatePhone")
                .email("updateEmail")
                .address(null)
                .build();
        memberService.updateInfo(1L, memberResponse);

        //then
        assertNotNull(memberResponse);
        assertEquals("updatePhone", memberResponse.getPhone());
        assertEquals("updateEmail", memberResponse.getEmail());
        assertEquals(null, memberResponse.getAddress());
    }

    @Test
    void updatePassword() {
        //given
        MemberRequest memberRequest = new MemberRequest(
                "loginId",
                "password",
                "password",
                "name",
                29,
                "phone",
                "email",
                "ROLE_USER",
                new Address("addr1", "addr2", "addr3"),
                null
        );

        memberService.join(memberRequest);

        //when
        MemberResponse memberResponse = MemberResponse.builder()
                .password("updatePassword")
                .confirmPassword("updatePassword")
                .build();
        memberService.updatePassword(1L, memberResponse);

        //then
        assertNotNull(memberResponse);
        assertEquals("updatePassword", memberResponse.getPassword());
        assertEquals("updatePassword", memberResponse.getConfirmPassword());
    }

    @Test
    void delete() {
        //given
        MemberRequest memberRequest = new MemberRequest(
                "loginId",
                "password",
                "password",
                "name",
                29,
                "phone",
                "email",
                "ROLE_USER",
                new Address("addr1", "addr2", "addr3"),
                null
        );

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