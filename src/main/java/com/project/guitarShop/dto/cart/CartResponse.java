package com.project.guitarShop.dto.cart;

import com.project.guitarShop.domain.cart.Cart;
import com.project.guitarShop.domain.orderItem.OrderItem;
import lombok.Getter;

import java.util.List;

public class CartResponse {

    @Getter
    public static class AddCartResponse {
        private final Long id;
        private final Long memberId;
        private final Long orderId;
        private final List<OrderItem> orderItems;

        public AddCartResponse(Cart cart) {
            this.id = cart.getId();
            this.memberId = cart.getMember().getId();
            this.orderId = cart.getOrder().getId();
            this.orderItems = cart.getOrderItems();
        }
    }
}


