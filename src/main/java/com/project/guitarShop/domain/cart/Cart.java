package com.project.guitarShop.domain.cart;

import com.project.guitarShop.domain.member.Member;
import com.project.guitarShop.domain.orderItem.OrderItem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();




    public static Cart addCart(Member member, List<OrderItem> orderItems) {
        Cart cart = new Cart();
        cart.member = member;
        cart.orderItems = new ArrayList<>(orderItems);
        return cart;
    }
}
