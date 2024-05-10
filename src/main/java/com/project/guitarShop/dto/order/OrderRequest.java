package com.project.guitarShop.dto.order;

import com.project.guitarShop.domain.address.Address;
import lombok.Builder;
import lombok.Getter;

public class OrderRequest {

    @Getter
    @Builder
    public static class CreateOrderRequest {
        private final Long memberId;
        private final Long itemId;
        private final int quantity;
        private Address deliveryAddress;
    }
}