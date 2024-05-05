package com.project.guitarShop.domain.board;

import com.project.guitarShop.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;
    private String content;
    private String password;

    public Board(Member member, String title, String content, String password, BCryptPasswordEncoder passwordEncoder) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.password = passwordEncoder.encode(password);
    }

    public boolean checkPassword(String password, BCryptPasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, this.password);
    }

    public void update(String title, String content, String password, BCryptPasswordEncoder passwordEncoder) {
        if (password != null && !password.isEmpty() && !passwordEncoder.matches(password, this.password)) {
            throw new IllegalArgumentException("패스워드 검증 실패");
        }
        this.title = title;
        this.content = content;
    }

}
