package com.project.guitarShop.domain.login;

import com.project.guitarShop.domain.member.Member;
import com.project.guitarShop.domain.member.MemberMapper;
import com.project.guitarShop.domain.member.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class LoginServiceTest {

    private LoginService loginService;
    private MemberMapper memberMapper;

    @BeforeEach
    public void setUp() throws Exception {
        memberMapper = mock(MemberMapper.class);
        loginService = new LoginService(memberMapper);
    }

    @Test
    void login() throws Exception {
        String testLoginId = "testId";
        String testPassword = "testPass";
        Member member = new Member(1L, testLoginId, testPassword, "Test User", 30, "Male", "test@example.com", "1990-01-01", "1234567890", "Test Address", Role.MEMBER);

    }
}