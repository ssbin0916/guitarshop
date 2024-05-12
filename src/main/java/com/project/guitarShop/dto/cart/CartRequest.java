package com.project.guitarShop.dto.cart;

import com.project.guitarShop.domain.orderItem.OrderItem;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class CartRequest {

    @Getter
    @Builder
    public static class AddCartRequest {
        private final Long memberId;
        private final Long orderId;
        private final List<OrderItem> orderItems = new ArrayList<>();
    }
}
