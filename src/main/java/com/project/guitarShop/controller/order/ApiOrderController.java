package com.project.guitarShop.controller.order;

import com.project.guitarShop.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiOrderController {

    private final OrderService orderService;

    @PostMapping("/{memberId}/{itemId}/order")
    public ResponseEntity<Long> order(@PathVariable Long memberId, @PathVariable Long itemId) {

        Long order = orderService.order(memberId, itemId);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<String> cancel(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok("주문이 취소되었습니다.");
    }

    @PostMapping("/{cartId}/orderFromCart")
    public ResponseEntity<Long> orderFormCart(@PathVariable Long cartId) {

        Long order = orderService.orderFromCart(cartId);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

}