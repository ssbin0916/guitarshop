package com.project.guitarShop.entity.order;

import com.project.guitarShop.entity.cartItem.CartItem;
import com.project.guitarShop.entity.delivery.Delivery;
import com.project.guitarShop.entity.delivery.DeliveryStatus;
import com.project.guitarShop.entity.item.Item;
import com.project.guitarShop.entity.member.Member;
import com.project.guitarShop.entity.orderItem.OrderItem;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(cascade = ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany(cascade = ALL)
    private List<CartItem> cartItems = new ArrayList<>();

    @OneToOne(cascade = ALL, fetch = LAZY)
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private Integer price;

    @Builder
    public Order(Member member, List<OrderItem> orderItems, List<CartItem> cartItems, Delivery delivery, LocalDateTime orderDate, OrderStatus orderStatus, Integer price) {
        this.member = member;
        this.orderItems = orderItems == null ? new ArrayList<>() : new ArrayList<>(orderItems);
        this.cartItems = cartItems == null ? new ArrayList<>() : new ArrayList<>(cartItems);
        this.delivery = delivery;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.price = price;
    }

    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMPLETE) {
            throw new IllegalStateException("이미 배송 완료된 상품은 취소가 불가능합니다.");
        }
        this.orderStatus = OrderStatus.CANCEL;
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }
}

