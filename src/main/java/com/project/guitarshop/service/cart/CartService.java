package com.project.guitarshop.service.cart;

public interface CartService {

    Long addCart(Long memberId, Long itemId);

    void remove(Long cartId);
}
