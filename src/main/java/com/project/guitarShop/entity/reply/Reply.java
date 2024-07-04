package com.project.guitarShop.entity.reply;

import com.project.guitarShop.entity.BaseEntity;
import com.project.guitarShop.entity.post.Post;
import com.project.guitarShop.entity.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String reply;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private Post post;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Reply(String reply, Post post, Member member) {
        this.reply = reply;
        this.post = post;
        this.member = member;
    }

    public void updateReply(String reply) {
        this.reply = reply;
    }
}
