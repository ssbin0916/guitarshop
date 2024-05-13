package com.project.guitarShop.entity.cart;

import com.project.guitarShop.entity.cartItem.CartItem;
import com.project.guitarShop.entity.member.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(cascade = ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    private Integer price;

    @Builder
    public Cart(Member member, List<CartItem> cartItems, Integer price) {
        this.member = member;
        this.cartItems = new ArrayList<>(cartItems);
        this.price = price;
    }

    public void remove() {
        for (CartItem cartItem : cartItems) {
            cartItem.remove();
        }
    }
}
