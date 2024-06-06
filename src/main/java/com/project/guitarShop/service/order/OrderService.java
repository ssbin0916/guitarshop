package com.project.guitarShop.service.order;

public interface OrderService {

    Long order(Long memberId, Long itemId);

    void cancelOrder(Long orderId);

    Long orderFromCart(Long cartId);
}
