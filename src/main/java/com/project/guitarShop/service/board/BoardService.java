package com.project.guitarShop.service.board;

import com.project.guitarShop.dto.board.BoardRequest.*;
import com.project.guitarShop.dto.board.BoardResponse.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {

    BoardWriteResponse createBoard(BoardWriteRequest request);

    Page<BoardListResponse> boardList(Pageable pageable);

    BoardReadResponse readBoard(Long boardId);

    BoardUpdateResponse updateBoard(Long boardId, BoardUpdateRequest request);

}
