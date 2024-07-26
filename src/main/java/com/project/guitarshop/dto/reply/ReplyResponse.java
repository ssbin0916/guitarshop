package com.project.guitarshop.dto.reply;

import com.project.guitarshop.entity.reply.Reply;

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
