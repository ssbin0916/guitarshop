package com.project.guitarshop.service.order;

import com.project.guitarshop.dto.order.OrderResponse.*;

public interface OrderService {

    CreateOrderResponse order(Long itemId, Integer quantity) throws InterruptedException;

    void cancel(Long id) throws InterruptedException;

    CreateOrderFromCartResponse orderFromCart(Long cartId, Integer quantity) throws InterruptedException;
}
