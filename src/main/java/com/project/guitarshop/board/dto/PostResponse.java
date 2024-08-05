package com.project.guitarshop.board.dto;

import com.project.guitarshop.board.entity.Post;
import com.project.guitarshop.board.entity.Reply;

import java.util.ArrayList;
import java.util.List;

public class PostResponse {

    public record PostWriteResponse(
            String title,
            String content,
            String username
    ) {
        public PostWriteResponse(Post post) {
            this(
                    post.getTitle(),
                    post.getContent(),
                    post.getMember().getName()
            );
        }
    }

    public record PostListResponse(
            String title,
            String username
    ) {
        public PostListResponse(Post post) {
            this(
                    post.getTitle(),
                    post.getMember().getName(
                    ));
        }
    }

    public record PostReadResponse(
            String title,
            String content,
            String username,
            List<Reply> replies
    ) {
        public PostReadResponse(Post post) {
            this(
                    post.getTitle(),
                    post.getContent(),
                    post.getMember().getName(),
                    post.getReplies() != null ? post.getReplies() : new ArrayList<>()
            );
        }
    }


    public record PostUpdateResponse(
            String title,
            String content,
            String username,
            List<Reply> replies
    ) {

        public PostUpdateResponse(Post post) {
            this(
                    post.getTitle(),
                    post.getContent(),
                    post.getMember().getName(),
                    post.getReplies() != null ? post.getReplies() : new ArrayList<>()
            );
        }
    }
}
