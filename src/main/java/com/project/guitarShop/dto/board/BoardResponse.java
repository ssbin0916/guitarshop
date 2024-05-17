package com.project.guitarShop.dto.board;

import com.project.guitarShop.entity.board.Board;
import com.project.guitarShop.entity.comment.Comment;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class BoardResponse {

    @Getter
    public static class BoardWriteResponse {
        private final Long memberId;
        private final String title;
        private final String content;
        private final LocalDateTime createDate;
        private final LocalDateTime updateDate;

        public BoardWriteResponse(Board board) {
            this.memberId = board.getMember().getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.createDate = board.getCreateDate();
            this.updateDate = board.getUpdateDate();
        }
    }

    @Getter
    public static class BoardListResponse {
        private final Long boardId;
        private final String memberName;
        private final String title;
        private final LocalDateTime createDate;
        private final LocalDateTime updateDate;

        public BoardListResponse(Board board) {
            this.boardId = board.getId();
            this.memberName = board.getMember().getName();
            this.title = board.getTitle();
            this.createDate = board.getCreateDate();
            this.updateDate = board.getUpdateDate();
        }
    }

    @Getter
    public static class BoardReadResponse {
        private final Long boardId;
        private final Long memberId;
        private final String title;
        private final String content;
        private final LocalDateTime createDate;
        private final LocalDateTime updateDate;

        public BoardReadResponse(Board board) {
            this.boardId = board.getId();
            this.memberId = board.getMember().getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.createDate = board.getCreateDate();
            this.updateDate = board.getUpdateDate();
        }
    }

    @Getter
    public static class BoardUpdateResponse {
        private final Long boardId;
        private final String title;
        private final String content;
        private final LocalDateTime createDate;
        private final LocalDateTime updateDate;

        public BoardUpdateResponse(Board board) {
            this.boardId = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.createDate = board.getCreateDate();
            this.updateDate = board.getUpdateDate();
        }
    }

    @Getter
    public static class BoardCommentResponse {
        private final Long id;
        private final String writer;
        private final String content;
        private final Integer view;
        private final Long memberId;
        private final List<Comment> comments;

        public BoardCommentResponse(Board board) {
            this.id = board.getId();
            this.writer = board.getWriter();
            this.content = board.getContent();
            this.view = board.getView();
            this.memberId = board.getMember().getId();
            this.comments = board.getComments();
        }
    }
}
