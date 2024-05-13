package com.project.guitarShop.dto.order;

import com.project.guitarShop.entity.address.Address;
import com.project.guitarShop.entity.cartItem.CartItem;
import lombok.Builder;
import lombok.Getter;

public class OrderRequest {

    @Getter
    @Builder
    public static class CreateOrderRequest {
        private final Long memberId;
        private final Long itemId;
        private Address deliveryAddress;
    }

    @Getter
    @Builder
    public static class CancelOrderRequest {
        private final Long orderId;
        private final Long itemId;
    }

    @Getter
    @Builder
    public static class OrderFormCartRequest {
        private final Long cartId;
        private final Long memberId;
        private final CartItem cartItem;
        private Address deliveryAddress;
    }
}