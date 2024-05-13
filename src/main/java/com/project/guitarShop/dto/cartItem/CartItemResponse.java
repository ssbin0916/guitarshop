package com.project.guitarShop.dto.cartItem;

import com.project.guitarShop.entity.cartItem.CartItem;
import com.project.guitarShop.entity.item.Item;
import lombok.Builder;
import lombok.Getter;

public class CartItemResponse {

    @Getter
    @Builder
    public static class AddCartItemResponse {
        private final CartItem cartItem;
        private final Item item;
        private final Integer price;
        private final Integer quantity;
    }
}
