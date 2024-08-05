package com.project.guitarshop.member.entity;

import com.project.guitarshop.global.entity.BaseEntity;
import com.project.guitarshop.board.entity.Post;
import com.project.guitarshop.cart.entity.Cart;
import com.project.guitarshop.board.entity.Reply;
import com.project.guitarshop.order.entity.Order;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseEntity {

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

    @OneToMany(mappedBy = "member", cascade = ALL)
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = ALL)
    private List<Cart> carts = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = ALL)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = ALL)
    private List<Reply> replies = new ArrayList<>();

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
