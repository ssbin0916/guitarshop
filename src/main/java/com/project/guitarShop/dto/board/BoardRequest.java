package com.project.guitarShop.dto.board;

import lombok.Builder;
import lombok.Getter;

public class BoardRequest {

    @Getter
    @Builder
    public static class BoardWriteRequest {
        private final Long memberId;
        private final String title;
        private final String content;

    }

    @Getter
    @Builder
    public static class BoardUpdateRequest {
        private final String title;
        private final String content;
    }

    @Getter
    @Builder
    public static class BoardCommentRequest {
        private final String comment;
    }

}
