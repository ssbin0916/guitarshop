package com.project.guitarshop.service.cart;

public interface CartService {

    Long addCart(Long itemId);

    void remove(Long cartId);
}
