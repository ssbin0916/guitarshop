package com.project.guitarShop.web.orders;

import com.project.guitarShop.domain.orderItem.OrderItem;
import com.project.guitarShop.domain.orders.Order;
import com.project.guitarShop.domain.orders.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody Order order, @RequestBody List<OrderItem> orderItems) {
        return orderService.saveOrderWithItems(order, orderItems);
    }

    @GetMapping("/member/{memberId}")
    public List<Order> orderList(@PathVariable("memberId") Long memberId) {
        return orderService.findByMemberId(memberId);
    }

    @GetMapping("/{orderId}")
    public Order orderDetail(@PathVariable("orderId") Long orderId) {
        return null;
    }
}
