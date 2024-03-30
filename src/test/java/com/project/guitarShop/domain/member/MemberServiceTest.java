package com.project.guitarShop.domain.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.project.guitarShop.domain.member.Role.MEMBER;
import static org.mockito.Mockito.*;

class MemberServiceTest {

    private MemberService memberService;
    private MemberMapper memberMapper;

    @BeforeEach
    public void setUp() throws Exception {
        memberMapper = mock(MemberMapper.class);
        memberService = new MemberService(memberMapper);
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() throws Exception {
        //given
        Long id = 1L;

        //when
        memberService.findById(id);

        //then
        verify(memberMapper, times(1)).findById(id);
    }

    @Test
    void save() throws Exception {
        Member member = new Member(1L, "username", "password", "Name", 30, "Male", "email@example.com", "1990-01-01", "9876543210", "Address", MEMBER);

        memberService.save(member);

        verify(memberMapper, times(1)).save(member);
    }

    @Test
    void update() throws Exception {
        //given

        //when

        //then
    }

    @Test
    void delete() {
        Member member = new Member(1L, "username", "password", "Name", 30, "Male", "email@example.com", "1990-01-01", "9876543210", "Address", MEMBER);

        Long id = 1L;

        memberService.delete(id);

        verify(memberMapper, times(1)).delete(id);


    }
}