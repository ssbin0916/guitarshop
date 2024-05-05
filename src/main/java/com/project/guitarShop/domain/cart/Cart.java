package com.project.guitarShop.domain.cart;

import com.project.guitarShop.domain.member.Member;
import com.project.guitarShop.domain.orderItem.OrderItem;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
    private List<OrderItem> orderItems;

    public Cart(Member member) {
        this.member = member;
    }

    public void addItem(OrderItem item) {
        orderItems.add(item);
        item.setCart(this);
    }

    public void removeItem(OrderItem item) {
        orderItems.remove(item);
        item.setCart(null);
    }
}
