package com.project.guitarShop.entity.orderItem;

import com.project.guitarShop.entity.item.Item;
import com.project.guitarShop.entity.order.Order;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    private String name;
    private Integer price;
    private Integer quantity;

    @Builder
    public OrderItem(Item item, String name, Integer price, Integer quantity) {
        this.item = item;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void addOrder(Order order) {
        this.order = order;
    }

    public static OrderItem createOrderItem(Item item, String name, Integer price) {
        OrderItem orderItem = new OrderItem();
        orderItem.item = item;
        orderItem.name = name;
        orderItem.price = price;

        item.removeStock(1);
        return orderItem;
    }

    public void cancel() {
        if (this.item != null) {
            getItem().addStock(1);
        }
    }

    public Integer getTotalPrice() {
        return getPrice();
    }

}
