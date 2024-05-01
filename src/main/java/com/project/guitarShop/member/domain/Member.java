package com.project.guitarShop.member.domain;

import com.project.guitarShop.address.domain.Address;
import com.project.guitarShop.order.domain.Order;
import com.project.guitarShop.member.app.MemberRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String confirmPassword;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String role;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Member(String loginId, String password, String confirmPassword, String name, int age, String phone, String email, String role, Address address, List<Order> orders) {
        Assert.notNull(loginId, "아이디 입력은 필수입니다.");
        Assert.notNull(password, "비밀번호 입력은 필수입니다.");
        Assert.notNull(confirmPassword, "비밀번호 확인 입력은 필수입니다.");
        Assert.notNull(name, "이름 입력은 필수입니다.");
        Assert.notNull(age, "나이 입력은 필수입니다.");
        Assert.notNull(phone, "전화번호 입력은 필수입니다.");
        Assert.notNull(email, "이메일 입력은 필수입니다.");
        Assert.notNull(address, "주소 입력은 필수입니다.");
        this.loginId = loginId;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.address = address;
        this.orders = orders;
    }

    public static Member toDomain(MemberRequest memberRequest) {
        return Member.builder()
                .loginId(memberRequest.getLoginId())
                .password(memberRequest.getPassword())
                .confirmPassword(memberRequest.getConfirmPassword())
                .name(memberRequest.getName())
                .age(memberRequest.getAge())
                .phone(memberRequest.getPhone())
                .email(memberRequest.getEmail())
                .role(memberRequest.getRole())
                .address(memberRequest.getAddress())
                .build();
    }
}
