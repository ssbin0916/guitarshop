package com.project.guitarShop.service.board;

import com.project.guitarShop.domain.board.Board;
import com.project.guitarShop.domain.member.Member;
import com.project.guitarShop.exception.NotFoundMemberException;
import com.project.guitarShop.exception.board.NotFoundBoardException;
import com.project.guitarShop.repository.board.BoardRepository;
import com.project.guitarShop.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public Board createBoard(Long memberId, String title, String content, Integer password) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundMemberException("해당 회원을 찾을 수 없습니다."));

        Board board = new Board(member, title, content, password);

        boardRepository.save(board);

        board.prePersist();

        return board;
    }

    public Board readBoard(Long boardId, Integer password) {

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundBoardException("해당 게시글을 찾을 수 없습니다."));

        if (!board.checkPassword(password) && !isAdmin()) {
            throw new IllegalArgumentException("해당 게시글을 열람할 수 있는 권한이 없습니다.");
        }

        board.checkPassword(password);

        return board;
    }

    public Board updateBoard(Long boardId, String title, String content) {

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundBoardException("해당 게시글을 찾을 수 없습니다."));

        board.checkPassword(board.getPassword());

        board.updateBoard(title, content);

        boardRepository.save(board);

        return board;
    }

    private boolean isAdmin() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        return authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
    }
}
