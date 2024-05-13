package com.project.guitarShop.dto.cart;

import lombok.Builder;
import lombok.Getter;

public class CartRequest {

    @Getter
    @Builder
    public static class AddCartRequest {
        private final Long memberId;
        private final Long itemId;
        private final Integer quantity;
    }
}
