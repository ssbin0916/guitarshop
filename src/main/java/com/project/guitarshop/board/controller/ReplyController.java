package com.project.guitarshop.board.controller;

import com.project.guitarshop.board.dto.ReplyRequest.*;
import com.project.guitarshop.board.dto.ReplyResponse.*;
import com.project.guitarshop.board.service.ReplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reply")
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/{postId}")
    public ResponseEntity<ReplyWriteResponse> reply(@PathVariable Long postId, @Valid @RequestBody ReplyWriteRequest request) {
        ReplyWriteResponse reply = replyService.reply(postId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(reply);
    }

    @PutMapping("/{replyId}")
    public ResponseEntity<ReplyUpdateResponse> update(@PathVariable Long replyId, @Valid @RequestBody ReplyUpdateRequest request) {
        return ResponseEntity.ok(replyService.update(replyId, request));
    }

    @DeleteMapping("/{replyId}")
    public ResponseEntity<Void> delete(@PathVariable Long replyId) {
        replyService.delete(replyId);
        return ResponseEntity.noContent().build();
    }

}
