package com.project.guitarShop.controller.order;

import com.project.guitarShop.dto.order.OrderResponse.*;
import com.project.guitarShop.service.order.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{memberId}/{itemId}/order")
    public ResponseEntity<CreateOrderResponse> order(@Valid @PathVariable Long memberId, @Valid @PathVariable Long itemId) throws InterruptedException {
        CreateOrderResponse order = orderService.order(memberId, itemId);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<String> cancel(@Valid @PathVariable Long id) throws InterruptedException {
        return ResponseEntity.ok("주문이 취소되었습니다.");
    }

    @PostMapping("/orderFromCart/{cartId}")
    public ResponseEntity<CreateOrderFromCartResponse> orderFromCart(@Valid @PathVariable Long cartId) throws InterruptedException {
        CreateOrderFromCartResponse order = orderService.orderFromCart(cartId);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

}
