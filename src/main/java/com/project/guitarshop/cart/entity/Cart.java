package com.project.guitarshop.cart.entity;

import com.project.guitarshop.member.entity.Member;
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
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "cart", cascade = ALL)
    private List<CartItem> cartItems = new ArrayList<>();

    @Builder
    public Cart(Member member) {
        this.member = member;
    }

    public void addMember(Member member) {
        this.member = member;
        member.getCarts().add(this);
    }

    public void addCartItem(CartItem cartItem) {
        this.cartItems.add(cartItem);
        cartItem.addCart(this);
    }

    public static Cart createCart(Member member, CartItem... cartItems) {
        Cart cart = new Cart();
        cart.addMember(member);
        for (CartItem cartItem : cartItems) {
            cart.addCartItem(cartItem);
        }
        return cart;
    }

    public Integer getTotalPrice() {
        Integer totalPrice = 0;
        for (CartItem cartItem : cartItems) {
            totalPrice += cartItem.getTotalPrice();
        }
        return totalPrice;
    }

    public void remove() {
        for (CartItem cartItem : cartItems) {
            cartItem.remove();
        }
    }

}
