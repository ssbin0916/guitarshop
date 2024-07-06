package com.project.guitarShop.service.order;

import com.project.guitarShop.dto.order.OrderResponse.*;

public interface OrderService {

    CreateOrderResponse order(Long memberId, Long itemId, Integer quantity) throws InterruptedException;

    void cancel(Long id) throws InterruptedException;

    CreateOrderFromCartResponse orderFromCart(Long cartId, Integer quantity) throws InterruptedException;
}
