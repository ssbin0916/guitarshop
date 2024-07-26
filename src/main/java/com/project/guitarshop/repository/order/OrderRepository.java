package com.project.guitarshop.repository.order;

import com.project.guitarshop.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
