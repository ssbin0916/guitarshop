package com.project.guitarshop.order.service;

import com.project.guitarshop.order.dto.OrderResponse.*;

public interface OrderService {

    CreateOrderResponse order(Long itemId, Integer quantity) throws InterruptedException;

    void cancel(Long id) throws InterruptedException;

    CreateOrderFromCartResponse orderFromCart(Long cartId, Integer quantity) throws InterruptedException;
}
