package com.project.guitarShop.entity.comment;

import com.project.guitarShop.entity.BaseTime;
import com.project.guitarShop.entity.board.Board;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Comment extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent", cascade = ALL)
    private List<Comment> child = new ArrayList<>();

    private Integer depth;

    public Comment(String content, Board board, Comment parent, List<Comment> child, Integer depth) {
        this.content = content;
        this.board = board;
        this.parent = parent;
        this.child = child;
        this.depth = depth;
    }
}
