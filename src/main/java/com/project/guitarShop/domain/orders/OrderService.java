package com.project.guitarShop.domain.orders;

import com.project.guitarShop.domain.orderItems.OrderItem;
import com.project.guitarShop.domain.orderItems.OrderItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    @Transactional
    public Order saveOrderWithItems(Order order, List<OrderItem> orderItems) {
        orderMapper.insert(order);
        orderItems.forEach(item -> {
            item.setOrderId(order.getId());
            orderItemMapper.save(item);
        });
        return order;
    }

    @Transactional(readOnly = true)
    public Order orderDetail(Long orderId) {
        Order order = orderMapper.findById(orderId);
        List<OrderItem> items = orderItemMapper.findByOrderId(orderId);
        order.setOrderItems(items);
        return order;
    }

    @Transactional(readOnly = true)
    public List<Order> findByMemberId(Long memberId) {
        return orderMapper.findByMemberId(memberId);
    }

    @Transactional
    public Order save(Order order) {
        orderMapper.insert(order);
        return order;
    }

    @Transactional
    public void update(Order order) {
        orderMapper.update(order);
    }

    @Transactional
    public void delete(Long id) {
        orderMapper.delete(id);
    }
}
