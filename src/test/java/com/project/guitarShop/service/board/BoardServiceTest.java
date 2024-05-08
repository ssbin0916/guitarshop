package com.project.guitarShop.service.board;

import com.project.guitarShop.domain.address.Address;
import com.project.guitarShop.domain.board.Board;
import com.project.guitarShop.dto.member.MemberRequest.JoinRequest;
import com.project.guitarShop.dto.member.MemberResponse.JoinResponse;
import com.project.guitarShop.service.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired
    BoardService boardService;
    @Autowired
    MemberService memberService;

    @Test
    void writeBoard() {
        //given
        JoinRequest memberRequest = JoinRequest.builder()
                .loginId("loginId")
                .password("password")
                .confirmPassword("password")
                .name("name")
                .rrn("000000-1111111")
                .phone("phone")
                .email("email")
                .address(new Address("add", "re", "ss"))
                .build();

        JoinResponse memberResponse = memberService.join(memberRequest);

        //when
        Board board = boardService.createBoard(memberResponse.getId(), "제목", "내용", 1234);

        //then
        assertEquals(1L, board.getId());

    }

    @Test
    void readBoard() {
        //given
        JoinRequest memberRequest = JoinRequest.builder()
                .loginId("loginId")
                .password("password")
                .confirmPassword("password")
                .name("name")
                .rrn("000000-1111111")
                .phone("phone")
                .email("email")
                .address(new Address("add", "re", "ss"))
                .build();

        JoinResponse memberResponse = memberService.join(memberRequest);

        Board board = boardService.createBoard(memberResponse.getId(), "제목", "내용", 1234);

        //when
        Board readBoard = boardService.readBoard(board.getId(), board.getPassword());

        //then
        assertEquals(1234, readBoard.getPassword());
    }

    @Test
    void updateBoard() {
        //given
        JoinRequest memberRequest = JoinRequest.builder()
                .loginId("loginId")
                .password("password")
                .confirmPassword("password")
                .name("name")
                .rrn("000000-1111111")
                .phone("phone")
                .email("email")
                .address(new Address("add", "re", "ss"))
                .build();

        JoinResponse memberResponse = memberService.join(memberRequest);

        Board board = boardService.createBoard(memberResponse.getId(), "제목", "내용", 1234);

        //when
        Board updateBoard = boardService.updateBoard(board.getId(), "제목 수정", "내용 수정");

        //then
        assertEquals("제목 수정", updateBoard.getTitle());
        assertEquals("내용 수정", updateBoard.getContent());
        assertEquals(1234, updateBoard.getPassword());
    }

}