package com.project.guitarShop.domain.order;

import com.project.guitarShop.domain.delivery.Delivery;
import com.project.guitarShop.domain.delivery.DeliveryStatus;
import com.project.guitarShop.domain.member.Member;
import com.project.guitarShop.domain.orderItem.OrderItem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.*;

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

    @OneToMany(cascade = ALL, fetch = LAZY, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(mappedBy = "order", cascade = ALL, fetch = LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    /**
     * 연관 관계 메서드
     */
    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public static Order createOrder(Member member, Delivery delivery, List<OrderItem> orderItems, LocalDateTime orderDate, OrderStatus orderStatus) {
        Order order = new Order();
        order.member = member;
        order.delivery = delivery;
        order.orderItems = new ArrayList<>(orderItems);
        order.orderDate = orderDate;
        order.orderStatus = orderStatus;
        return order;
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

    public int getTotalPrice() {
        return orderItems.stream().mapToInt(OrderItem::getTotalPrice).sum();
    }
}

