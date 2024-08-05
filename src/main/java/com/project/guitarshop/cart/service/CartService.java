package com.project.guitarshop.cart.service;

public interface CartService {

    Long addCart(Long itemId);

    void remove(Long cartId);
}
