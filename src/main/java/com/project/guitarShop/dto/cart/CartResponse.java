package com.project.guitarShop.dto.cart;

import com.project.guitarShop.entity.cart.Cart;
import com.project.guitarShop.entity.cartItem.CartItem;
import lombok.Getter;

import java.util.List;

public class CartResponse {

    @Getter
    public static class AddCartResponse {
        private final Long id;
        private final Long memberId;
        private final List<CartItem> cartItems;
        private final String name;
        private final Integer price;

        public AddCartResponse(Cart cart) {
            this.id = cart.getId();
            this.memberId = cart.getMember().getId();
            this.cartItems = cart.getCartItems();
            this.name = cart.getCartItems().get(0).getName();
            this.price = cart.getPrice();
        }
    }

    @Getter
    public static class CartItemDetails {
        private final Long itemId;
        private final String itemName;
        private final Integer price;

        public CartItemDetails(CartItem cartItem) {
            this.itemId = cartItem.getItem().getId();
            this.itemName = cartItem.getItem().getName();
            this.price = cartItem.getItem().getPrice();
        }
    }

    @Getter
    public static class UpdateQuantityResponse {
        private final Long itemId;
        private final String itemName;
        private final Integer price;

        public UpdateQuantityResponse(CartItem cartItem) {
            this.itemId = cartItem.getId();
            this.itemName = cartItem.getItem().getName();
            this.price = cartItem.getItem().getPrice();
        }
    }
}