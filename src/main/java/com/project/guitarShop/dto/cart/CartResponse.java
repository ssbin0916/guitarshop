package com.project.guitarShop.dto.cart;

import com.project.guitarShop.domain.cart.Cart;
import com.project.guitarShop.domain.orderItem.OrderItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

public class CartResponse {

    @Getter
    @Setter
    public static class AddCartResponse {

        private Long id;
        private Long memberId;
        private List<OrderItemResponse> orderItems;

        public AddCartResponse(Cart cart) {
            this.id = cart.getId();
            this.orderItems = cart.getOrderItems().stream()
                    .map(OrderItemResponse::new)
                    .collect(Collectors.toList());
        }
    }

    @Getter
    @Setter
    public static class OrderItemResponse {
        private Long id;
        private String itemName;
        private int price;
        private int count;

        public OrderItemResponse(OrderItem orderItem) {
            this.id = orderItem.getId();
            this.itemName = orderItem.getItem().getName();
            this.price = orderItem.getOrderPrice();
            this.count = orderItem.getQuantity();
        }
    }
}


