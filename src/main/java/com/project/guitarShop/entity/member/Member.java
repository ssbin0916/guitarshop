package com.project.guitarShop.entity.member;

import com.project.guitarShop.entity.BaseEntity;
import com.project.guitarShop.entity.address.Address;
import com.project.guitarShop.entity.board.Board;
import com.project.guitarShop.entity.cart.Cart;
import com.project.guitarShop.entity.comment.Comment;
import com.project.guitarShop.entity.order.Order;
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

    private String password;

    private String name;

    private String phone;

    private String role;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member", cascade = ALL)
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = ALL)
    private List<Cart> carts = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = ALL)
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = ALL)
    private List<Comment> comments = new ArrayList<>();

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
