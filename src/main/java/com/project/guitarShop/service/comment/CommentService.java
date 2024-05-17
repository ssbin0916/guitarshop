package com.project.guitarShop.service.comment;

import com.project.guitarShop.dto.comment.CommentRequest.*;
import com.project.guitarShop.dto.comment.CommentResponse.*;
import com.project.guitarShop.entity.board.Board;
import com.project.guitarShop.entity.comment.Comment;
import com.project.guitarShop.entity.member.Member;
import com.project.guitarShop.exception.board.NotFoundBoardException;
import com.project.guitarShop.exception.member.NotFoundMemberException;
import com.project.guitarShop.repository.board.BoardRepository;
import com.project.guitarShop.repository.comment.CommentRepository;
import com.project.guitarShop.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    public CommentWriteResponse comment(CommentWriteRequest request) {

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new NotFoundMemberException("해당 회원을 찾을 수 없습니다."));

        Board board = boardRepository.findById(request.getBoardId())
                .orElseThrow(() -> new NotFoundBoardException("해당 게시글을 찾을 수 없습니다."));

        Comment comment = new Comment(member, board, request.getComment());

        Comment save = commentRepository.save(comment);

        return new CommentWriteResponse(save);
    }
}
