package com.project.guitarShop.dto.order;

import com.project.guitarShop.entity.order.Order;

public class OrderResponse {

    public record CreateOrderResponse(
            String username,
            String itemName,
            Integer price,
            Integer quantity
    ) {
        public CreateOrderResponse(Order order) {
            this(
                    order.getMember().getName(),
                    order.getOrderItems().get(0).getName(),
                    order.getOrderItems().get(0).getPrice(),
                    order.getOrderItems().get(0).getQuantity()
            );
        }
    }

    public record CreateOrderFromCartResponse(
            String username,
            String itemName,
            Integer price,
            Integer quantity
    ) {
        public CreateOrderFromCartResponse(Order order) {
            this(
                    order.getMember().getName(),
                    order.getOrderItems().get(0).getName(),
                    order.getOrderItems().get(0).getPrice(),
                    order.getOrderItems().get(0).getQuantity()
            );
        }
    }

}
