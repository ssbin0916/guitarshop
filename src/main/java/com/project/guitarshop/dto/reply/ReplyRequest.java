package com.project.guitarshop.dto.reply;

public class ReplyRequest {

    public record ReplyWriteRequest(
            Long boardId,
            Long memberId,
            String reply
    ) {
    }

    public record ReplyUpdateRequest(
            String reply
    ) {
    }
}
