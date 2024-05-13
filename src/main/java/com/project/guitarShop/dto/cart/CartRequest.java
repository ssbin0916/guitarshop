package com.project.guitarShop.dto.cart;

import lombok.Builder;
import lombok.Getter;

public class CartRequest {

    @Getter
    @Builder
    public static class AddCartRequest {
        private final Long memberId;
        private final Long itemId;
    }

    @Getter
    @Builder
    public static class UpdateQuantityRequest {
        private final Long cartId;
        private final Long cartItemId;
    }
}
