package com.project.guitarshop.dto.order;

import com.project.guitarshop.entity.order.Order;

public class OrderResponse {

    public record CreateOrderResponse(
            String username,
            String itemName,
            Integer price,
            Integer quantity,
            Integer totalPrice
    ) {
        public CreateOrderResponse(Order order) {
            this(
                    order.getMember().getName(),
                    order.getOrderItems().get(0).getName(),
                    order.getOrderItems().get(0).getPrice(),
                    order.getOrderItems().get(0).getQuantity(),
                    order.getTotalPrice()
            );
        }
    }

    public record CreateOrderFromCartResponse(
            String username,
            String itemName,
            Integer price,
            Integer quantity,
            Integer totalPrice
    ) {
        public CreateOrderFromCartResponse(Order order) {
            this(
                    order.getMember().getName(),
                    order.getOrderItems().get(0).getName(),
                    order.getOrderItems().get(0).getPrice(),
                    order.getOrderItems().get(0).getQuantity(),
                    order.getTotalPrice()
            );
        }
    }

}
