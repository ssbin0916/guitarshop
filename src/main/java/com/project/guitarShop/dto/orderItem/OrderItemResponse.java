package com.project.guitarShop.dto.orderItem;

import com.project.guitarShop.domain.item.Item;
import com.project.guitarShop.domain.orderItem.*;
import com.project.guitarShop.dto.item.ItemResponse.*;
import lombok.Builder;
import lombok.Getter;

public class OrderItemResponse {

    @Getter
    @Builder
    public static class CreateOrderItemResponse {
        private final OrderItem orderItem;
        private final Item item;
        private final int orderPrice;
        private final int quantity;

    }

    public static class CancelOrderItemResponse {
        private final Long id;
        private final CancelItemResponse item;
        private final Integer quantity;

        public CancelOrderItemResponse(OrderItem orderItem) {
            this.id = orderItem.getId();
            this.item = new CancelItemResponse(orderItem.getItem());
            this.quantity = orderItem.getQuantity();
        }
    }

}
