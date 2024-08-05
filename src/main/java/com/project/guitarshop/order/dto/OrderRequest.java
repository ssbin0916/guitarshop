package com.project.guitarshop.order.dto;

import com.project.guitarshop.member.entity.Address;

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