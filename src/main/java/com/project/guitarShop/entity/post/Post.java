package com.project.guitarShop.entity.post;

import com.project.guitarShop.entity.BaseEntity;
import com.project.guitarShop.entity.reply.Reply;
import com.project.guitarShop.entity.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private Integer view;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "post", cascade = ALL)
    private List<Reply> replies = new ArrayList<>();

    @Builder
    public Post(String title, String content, Integer view, Member member, List<Reply> replies) {
        this.title = title;
        this.content = content;
        this.view = view;
        this.member = member;
        this.replies = replies;
    }

    public void updateBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void addViewCount(Integer view) {
        this.view = view;
    }


}
