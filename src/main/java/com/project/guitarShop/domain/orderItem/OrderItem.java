package com.project.guitarShop.domain.orderItem;

import com.project.guitarShop.domain.cart.Cart;
import com.project.guitarShop.domain.item.Item;
import com.project.guitarShop.domain.order.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    private int orderPrice;
    private int quantity;

    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        if (item == null) {
            throw new IllegalArgumentException("상품이 비어있으면 안됩니다.");
        }

        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setQuantity(count);

        return orderItem;
    }




    public OrderItem(Item item, int orderPrice, int quantity) {
        this.item = item;
        this.orderPrice = orderPrice;
        this.quantity = quantity;
    }

    public void cancel() {
        getItem().addStock(quantity);
    }

    public int getTotalPrice() {
        return getOrderPrice() * getQuantity();
    }
}
