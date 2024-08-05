package com.project.guitarshop.board.dto;

import com.project.guitarshop.board.entity.Reply;

public class ReplyResponse {

    public record ReplyWriteResponse(
            String boardTitle,
            String reply,
            String username
    ) {
        public ReplyWriteResponse(Reply reply) {
            this(
                    reply.getPost().getTitle(),
                    reply.getReply(),
                    reply.getMember().getName(
                    ));
        }
    }

    public record ReplyUpdateResponse(
            String boardTitle,
            String reply,
            String username
    ) {
        public ReplyUpdateResponse(Reply reply) {
            this(
                    reply.getPost().getTitle(),
                    reply.getReply(),
                    reply.getMember().getName(
                    ));
        }
    }
}
