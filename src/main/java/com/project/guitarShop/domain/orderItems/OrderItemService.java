package com.project.guitarShop.domain.orderItems;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemMapper orderItemMapper;

    @Transactional(readOnly = true)
    public List<OrderItem> findByOrderId(Long orderId) {
        return orderItemMapper.findByOrderId(orderId);
    }

    @Transactional
    public void save(OrderItem orderItem) {
        orderItemMapper.save(orderItem);
    }

    @Transactional
    public void update(OrderItem orderItem) {
        orderItemMapper.update(orderItem);
    }

    @Transactional
    public void delete(Long id) {
        orderItemMapper.delete(id);
    }
}
