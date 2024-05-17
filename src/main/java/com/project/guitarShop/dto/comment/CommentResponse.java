package com.project.guitarShop.dto.comment;

import com.project.guitarShop.entity.comment.Comment;
import lombok.Getter;

public class CommentResponse {

    @Getter
    public static class CommentWriteResponse {
        private final Long id;
        private final String comment;
        private final String memberName;
        private final Long boardId;

        public CommentWriteResponse(Comment comment) {
            this.id = comment.getId();
            this.comment = comment.getComment();
            this.memberName = comment.getMember().getName();
            this.boardId = comment.getBoard().getId();
        }
    }

}
