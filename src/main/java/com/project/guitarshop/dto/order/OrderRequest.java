package com.project.guitarshop.dto.order;

import com.project.guitarshop.entity.address.Address;

public class OrderRequest {

    public record CreateOrderRequest(
            Long memberId,
            Long itemId,
            Integer quantity,
            Address address
    ) {
    }

    public record CreateOrderFromCartRequest(
            Long memberId,
            Long itemId,
            Integer quantity,
            Address address
    ) {
    }
}