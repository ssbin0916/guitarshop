package com.project.guitarShop.repository.cartItem;

import com.project.guitarShop.entity.cartItem.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
