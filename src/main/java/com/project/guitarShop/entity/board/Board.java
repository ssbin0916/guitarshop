package com.project.guitarShop.entity.board;

import com.project.guitarShop.entity.BaseEntity;
import com.project.guitarShop.entity.comment.Comment;
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
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private String writer;

    private Integer view;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "board", cascade = ALL)
    @OrderBy("id asc")
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Board(Member member, String title, String content) {
        this.member = member;
        this.title = title;
        this.content = content;
    }

    public void updateBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }


}
