package com.project.guitarShop.domain.orderItem;

import com.project.guitarShop.domain.cart.Cart;
import com.project.guitarShop.domain.item.Item;
import com.project.guitarShop.domain.order.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    private int orderPrice;
    private int count;

    public static OrderItem createOrderItem(Optional<Item> item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item.get());
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.get().removeStock(count);
        return orderItem;
    }

    public void cancel() {
        getItem().addStock(count);
    }

    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
