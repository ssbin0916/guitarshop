package com.project.guitarShop.dto.orderItem;

import com.project.guitarShop.domain.item.Item;
import com.project.guitarShop.domain.orderItem.OrderItem;
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
}
