package com.project.guitarShop.repository.order;

import com.project.guitarShop.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
