package com.project.guitarShop.entity.orderItem;

import com.project.guitarShop.entity.cart.Cart;
import com.project.guitarShop.entity.item.Item;
import com.project.guitarShop.entity.order.Order;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    private String name;
    private Integer price;

    @Builder
    public OrderItem(Item item, String name, Integer price) {
        this.item = item;
        this.name = name;
        this.price = price;
    }


    public void cancel() {
        if (this.item != null) {
            getItem().addStock(1);
        }
    }
}
