package com.project.guitarShop.service.board;

import com.project.guitarShop.dto.board.BoardRequest.BoardUpdateRequest;
import com.project.guitarShop.dto.board.BoardRequest.BoardWriteRequest;
import com.project.guitarShop.dto.board.BoardResponse.BoardListResponse;
import com.project.guitarShop.dto.board.BoardResponse.BoardReadResponse;
import com.project.guitarShop.dto.board.BoardResponse.BoardUpdateResponse;
import com.project.guitarShop.dto.board.BoardResponse.BoardWriteResponse;
import com.project.guitarShop.entity.board.Board;
import com.project.guitarShop.entity.member.Member;
import com.project.guitarShop.exception.board.NotFoundBoardException;
import com.project.guitarShop.exception.member.NotFoundMemberException;
import com.project.guitarShop.repository.board.BoardRepository;
import com.project.guitarShop.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public BoardWriteResponse createBoard(BoardWriteRequest request) {

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new NotFoundMemberException("해당 회원을 찾을 수 없습니다."));

        Board board = new Board(request.getTitle(), request.getContent(), member);

        Board save = boardRepository.save(board);

        save.prePersist();

        return new BoardWriteResponse(save);
    }

    @Transactional
    @Override
    public Page<BoardListResponse> boardList(Pageable pageable) {
        return boardRepository.findAll(pageable).map(BoardListResponse::new);
    }

    @Transactional
    @Override
    public BoardReadResponse readBoard(Long boardId) {
        Board board  = boardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundBoardException("해당 게시글을 찾을 수 없습니다."));

        return new BoardReadResponse(board);
    }

    @Transactional
    @Override
    public BoardUpdateResponse updateBoard(Long boardId, BoardUpdateRequest request) {

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundBoardException("해당 게시글을 찾을 수 없습니다."));

        board.updateBoard(request.getTitle(), request.getContent());

        return new BoardUpdateResponse(boardRepository.save(board));
    }

}
