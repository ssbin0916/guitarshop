package com.project.guitarShop.controller.board;

import com.project.guitarShop.dto.board.BoardRequest.BoardUpdateRequest;
import com.project.guitarShop.dto.board.BoardRequest.BoardWriteRequest;
import com.project.guitarShop.dto.board.BoardResponse.BoardListResponse;
import com.project.guitarShop.dto.board.BoardResponse.BoardReadResponse;
import com.project.guitarShop.dto.board.BoardResponse.BoardUpdateResponse;
import com.project.guitarShop.dto.board.BoardResponse.BoardWriteResponse;
import com.project.guitarShop.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiBoardController {

    private final BoardService boardService;

    @PostMapping("/board")
    public ResponseEntity<BoardWriteResponse> createBoard(@RequestBody BoardWriteRequest request) {
        BoardWriteResponse board = boardService.createBoard(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(board);
    }

    @GetMapping("/board/list")
    public ResponseEntity<Page<BoardListResponse>> boardList(Pageable pageable) {
        Page<BoardListResponse> boardList = boardService.boardList(pageable);
        return ResponseEntity.ok().body(boardList);
    }

    @GetMapping("/board/{id}")
    public ResponseEntity<BoardReadResponse> getBoardById(@PathVariable Long id) {
        BoardReadResponse boardRead = boardService.readBoard(id);
        return ResponseEntity.ok().body(boardRead);
    }

    @PutMapping("/board/{id}")
    public ResponseEntity<BoardUpdateResponse> updateBoard(@PathVariable Long id, @RequestBody BoardUpdateRequest request) {
        BoardUpdateResponse boardUpdate = boardService.updateBoard(id, request);
        return ResponseEntity.ok().body(boardUpdate);
    }

}
