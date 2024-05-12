package com.project.guitarShop.entity.board;

import com.project.guitarShop.entity.BaseTime;
import com.project.guitarShop.entity.member.Member;
import com.project.guitarShop.exception.ValidatePasswordException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Board extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;
    private String content;
    private Integer password;

    public Board(Member member, String title, String content, Integer password) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.password = password;
    }

    public boolean checkPassword(Integer password) {
        if (!password.equals(this.password)) {
            throw new ValidatePasswordException("비밀번호가 틀렸습니다.");
        }
        return true;
    }

    public void updateBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
