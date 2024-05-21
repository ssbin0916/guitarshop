package com.project.guitarShop.controller.comment;

import com.project.guitarShop.dto.comment.CommentRequest.CommentWriteRequest;
import com.project.guitarShop.dto.comment.CommentResponse.CommentWriteResponse;
import com.project.guitarShop.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{id}/comment")
    public ResponseEntity<CommentWriteResponse> comment(@RequestBody CommentWriteRequest request) {
        CommentWriteResponse comment = commentService.comment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

}
