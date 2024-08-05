package com.project.guitarshop.order.controller;

import com.project.guitarshop.order.dto.OrderRequest.*;
import com.project.guitarshop.order.dto.OrderResponse.*;
import com.project.guitarshop.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{itemId}")
    public ResponseEntity<CreateOrderResponse> order(@PathVariable Long itemId, @Valid @RequestBody CreateOrderRequest request) throws InterruptedException {
        CreateOrderResponse orderResponse = orderService.order(itemId, request.quantity());
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }

    @PostMapping("/cancel/{orderId}")
    public ResponseEntity<Void> cancel(@PathVariable Long orderId) throws InterruptedException {
        orderService.cancel(orderId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/orderFromCart/{cartId}")
    public ResponseEntity<CreateOrderFromCartResponse> orderFromCart(@PathVariable Long cartId, @Valid @RequestBody CreateOrderFromCartRequest request) throws InterruptedException {
        CreateOrderFromCartResponse order = orderService.orderFromCart(cartId, request.quantity());
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

}
