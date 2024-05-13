package com.project.guitarShop.repository.cart;

import com.project.guitarShop.entity.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByMemberId(Long memberId);
}
