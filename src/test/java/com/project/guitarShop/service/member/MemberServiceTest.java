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
        memberService = new MemberService(memberRepository, bCryptPasswordEncoder);
    }

    @Test
    void join() {
        //given
        JoinRequest request = MemberRequest.JoinRequest.builder()
                .loginEmail("email@test.com")
                .password("password")
                .confirmPassword("password")
                .name("name")
                .phone("phone")
                .address(new Address("add", "re", "ss"))
                .build();

        //when
        JoinResponse response = memberService.join(request);

        //then

        assertNotNull(response);

        assertEquals("email@test.com", response.getLoginEmail());
        assertEquals("name", response.getName());
    }

    @Test
    void existLoginId() {
        //given
        JoinRequest request1 = createDummyJoinRequest();
        memberService.join(request1);

        //when
        JoinRequest request2 = createDummyJoinRequest();
        memberService.join(request2);

        //then
        assertThrows(ExistMemberException.class, () -> memberService.join(request2));
    }

    @Test
    void passwordNotMatch() {
        //given
        JoinRequest request = createDummyJoinRequest();
        memberService.join(request);

        //when //then
        assertThrows(ValidatePasswordException.class, () -> memberService.join(request));
    }

    @Test
    void login() {
        JoinRequest request = createDummyJoinRequest();
        memberService.join(request);

        //when
        LoginResponse response = memberService.login("email@test.com", "password");

        //then
        assertNotNull(response);
        assertEquals("email@test.com", response.getLoginEmail());
    }

    @Test
    void updateInfo() {
        //given
        JoinRequest request = createDummyJoinRequest();
        memberService.join(request);

        //when
        UpdateInfoRequest updateMember = UpdateInfoRequest.builder()
                .phone("updatePhone")
                .address(new Address("up", "da", "te"))
                .build();

        memberService.updateInfo(1L, updateMember);

        //then
        assertNotNull(updateMember);
        assertEquals("updatePhone", updateMember.getPhone());
    }

    @Test
    void updatePassword() {
        //given
        JoinRequest request = createDummyJoinRequest();
        JoinResponse response = memberService.join(request);

        //when
        UpdatePasswordRequest updatePasswordRequest = UpdatePasswordRequest.builder()
                .password("updatePassword")
                .confirmPassword("updatePassword")
                .build();

        memberService.updatePassword(response.getId(), updatePasswordRequest);


        //then
        Member updatedMember = memberRepository.findById(response.getId()).orElseThrow(() ->
                new NotFoundMemberException("찾을 수 없는 회원입니다."));

        assertTrue(bCryptPasswordEncoder.matches("updatePassword", updatedMember.getPassword()));
    }

    @Test
    void delete() {
        //given
        JoinRequest request = createDummyJoinRequest();
        memberService.join(request);

        //when
        memberService.delete(1L);

        //then
        assertThrows(NotFoundMemberException.class, () -> {
            memberRepository.findById(1L)
                    .orElseThrow(() -> new NotFoundMemberException("존재하지 않습니다."));
        });
    }

    private JoinRequest createDummyJoinRequest() {
        return MemberRequest.JoinRequest.builder()
                .loginEmail("email@test.com")
                .password("password")
                .confirmPassword("password")
                .name("name")
                .phone("phone")
                .address(new Address("add", "re", "ss"))
                .build();
    }

    private LoginResponse loginMember(String username, String password) {
        return memberService.login(username, password);
    }
}