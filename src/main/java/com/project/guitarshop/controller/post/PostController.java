package com.project.guitarshop.controller.post;

import com.project.guitarshop.dto.post.PostRequest.*;
import com.project.guitarshop.dto.post.PostResponse.*;
import com.project.guitarshop.service.post.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostWriteResponse> write(@Valid @RequestBody PostWriteRequest request) {
        PostWriteResponse board = postService.write(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(board);
    }

    @GetMapping
    public ResponseEntity<Page<PostListResponse>> list(Pageable pageable) {
        return ResponseEntity.ok(postService.list(pageable));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostReadResponse> read(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.read(postId));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostUpdateResponse> update(@PathVariable Long postId, @Valid @RequestBody PostUpdateRequest request) {
        return ResponseEntity.ok(postService.update(postId, request));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> delete(@PathVariable Long postId) {
        postService.delete(postId);
        return ResponseEntity.noContent().build();
    }

}
