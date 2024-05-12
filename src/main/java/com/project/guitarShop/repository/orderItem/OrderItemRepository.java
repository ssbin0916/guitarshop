package com.project.guitarShop.repository.orderItem;

import com.project.guitarShop.entity.orderItem.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
