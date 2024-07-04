package com.project.guitarShop.dto.order;

import com.project.guitarShop.entity.address.Address;

public class OrderRequest {

    public record CreateOrderRequest(
            Long memberId,
            Long itemId,
            Address address
    ) {
    }
}