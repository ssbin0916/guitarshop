package com.project.guitarShop.order.app;

public interface OrderService {

    Long order(Long memberId, Long itemId, int count);

    void cancelOrder(Long id);

}
