package com.project.guitarShop.member.domain;

import com.project.guitarShop.address.domain.Address;
import com.project.guitarShop.order.domain.Order;
import com.project.guitarShop.member.app.MemberRequest;
import jakarta.persistence.*;
import lombok.*;

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
    private Member(String loginId, String password, String confirmPassword, String name, int age, String phone, String email, String role, Address address, List<Order> orders) {
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

    public void updateInfo(String phone, String email, Address address) {
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public void updatePassword(String password, String confirmPassword) {
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public static Member toDomain(MemberRequest memberRequest) {
        return Member.builder()
                .loginId(memberRequest.loginId())
                .password(memberRequest.password())
                .confirmPassword(memberRequest.confirmPassword())
                .name(memberRequest.name())
                .age(memberRequest.age())
                .phone(memberRequest.phone())
                .email(memberRequest.email())
                .role(memberRequest.role())
                .address(memberRequest.address())
                .orders(memberRequest.orders())
                .build();
    }
}
