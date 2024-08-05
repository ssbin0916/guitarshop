package com.project.guitarshop.dto.reply;

public class ReplyRequest {

    public record ReplyWriteRequest(
            String reply
    ) {
    }

    public record ReplyUpdateRequest(
            String reply
    ) {
    }
}
