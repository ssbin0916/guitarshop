package com.project.guitarShop.domain.member;

import com.project.guitarShop.domain.address.Address;
import com.project.guitarShop.domain.order.Order;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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
    private String role;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Member(Long id, String loginId, String password, String confirmPassword, String name, int age, String phone, String email, String role, Address address, List<Order> orders) {
        this.id = id;
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
}
