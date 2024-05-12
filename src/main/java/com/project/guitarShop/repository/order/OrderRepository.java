package com.project.guitarShop.repository.order;

import com.project.guitarShop.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
