package com.project.guitarshop.board.dto;

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
