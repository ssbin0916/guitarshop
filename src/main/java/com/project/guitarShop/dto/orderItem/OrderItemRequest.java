package com.project.guitarShop.dto.orderItem;

import com.project.guitarShop.domain.item.Item;
import lombok.Builder;
import lombok.Getter;

public class OrderItemRequest {

    @Getter
    @Builder
    public static class CreateOrderItemRequest {
        private final Item item;
        private final int orderPrice;
        private final int count;
    }
}
