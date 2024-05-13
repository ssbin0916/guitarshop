package com.project.guitarShop.dto.cart;

import com.project.guitarShop.entity.cartItem.CartItem;
import com.project.guitarShop.entity.cart.Cart;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

public class CartResponse {

    @Getter
    public static class AddCartResponse {
        private final Long id;
        private final Long memberId;
        private List<CartItemDetails> cartItems;

        public AddCartResponse(Cart cart) {
            this.id = cart.getId();
            this.memberId = cart.getMember().getId();
            this.cartItems = cart.getCartItems().stream()
                    .map(cartItem -> new CartItemDetails(cartItem))
                    .collect(Collectors.toList());
        }
    }

    @Getter
    public static class CartItemDetails {
        private final Long itemId;
        private final String itemName;
        private final Integer price;
        private final Integer quantity;

        public CartItemDetails(CartItem cartItem) {
            this.itemId = cartItem.getItem().getId();
            this.itemName = cartItem.getItem().getName();
            this.price = cartItem.getItem().getPrice();
            this.quantity = cartItem.getQuantity();
        }
    }

}


