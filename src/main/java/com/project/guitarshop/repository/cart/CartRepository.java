package com.project.guitarshop.repository.cart;

import com.project.guitarshop.entity.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
