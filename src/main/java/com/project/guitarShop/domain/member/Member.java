package com.project.guitarShop.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.guitarShop.domain.BaseTime;
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
public class Member extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String loginEmail;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String role;

    @Embedded
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Member(String loginEmail, String password, String name, String phone, String role, Address address) {
        this.loginEmail = loginEmail;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.role = role;
        this.address = address;
    }

    public void updatePhone(String phone) {
        this.phone = phone;
    }

    public void updateAddress(Address address) {
        this.address = address;
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }
}
