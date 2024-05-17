package com.project.guitarShop.dto.comment;


import lombok.Builder;
import lombok.Getter;

public class CommentRequest {

    @Getter
    @Builder
    public static class CommentWriteRequest {
        private Long memberId;
        private Long boardId;
        private String comment;
    }
}
