package com.project.guitarShop.order.repository;

import com.project.guitarShop.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
