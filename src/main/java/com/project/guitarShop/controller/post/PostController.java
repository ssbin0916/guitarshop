package com.project.guitarShop.controller.post;

import com.project.guitarShop.dto.post.PostRequest.*;
import com.project.guitarShop.dto.post.PostResponse.*;
import com.project.guitarShop.service.post.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
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

    @GetMapping("/{id}")
    public ResponseEntity<PostReadResponse> read(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(postService.read(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostUpdateResponse> update(@Valid @PathVariable Long id, @Valid @RequestBody PostUpdateRequest request) {
        return ResponseEntity.ok(postService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@Valid @PathVariable Long id) {
        return ResponseEntity.ok("게시글 삭제 완료");
    }

}
