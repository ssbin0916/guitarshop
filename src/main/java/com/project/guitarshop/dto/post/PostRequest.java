package com.project.guitarshop.dto.post;

public class PostRequest {

    public record PostWriteRequest(
            String title,
            String content
    ) {
    }

    public record PostUpdateRequest(
            String title,
            String content
    ) {
    }
}
