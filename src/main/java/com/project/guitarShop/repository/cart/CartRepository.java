package com.project.guitarShop.repository.cart;

import com.project.guitarShop.domain.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
