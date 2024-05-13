package com.project.guitarShop.entity.cartItem;

import com.project.guitarShop.entity.cart.Cart;
import com.project.guitarShop.entity.item.Item;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private String name;
    private Integer price;

    @Builder
    public CartItem(Item item, String name, Integer price) {
        this.item = item;
        this.name = name;
        this.price = price;
    }

    public void remove() {
        if (this.item != null) {
            getItem().addStock(1);
        }
    }
}
