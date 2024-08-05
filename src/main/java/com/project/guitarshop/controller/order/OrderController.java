package com.project.guitarshop.controller.order;

import com.project.guitarshop.dto.order.OrderRequest.*;
import com.project.guitarshop.dto.order.OrderResponse.*;
import com.project.guitarshop.service.order.OrderService;
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

    @PostMapping("/order/{itemId}")
    public ResponseEntity<CreateOrderResponse> order(@PathVariable Long itemId, @Valid @RequestBody CreateOrderRequest request) throws InterruptedException {
        CreateOrderResponse orderResponse = orderService.order(itemId, request.quantity());
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }

    @PostMapping("/cancel/{orderId}")
    public ResponseEntity<String> cancel(@PathVariable Long orderId) throws InterruptedException {
        return ResponseEntity.ok("주문이 취소되었습니다.");
    }

    @PostMapping("/orderFromCart/{cartId}")
    public ResponseEntity<CreateOrderFromCartResponse> orderFromCart(@PathVariable Long cartId, @Valid @RequestBody CreateOrderFromCartRequest request) throws InterruptedException {
        CreateOrderFromCartResponse order = orderService.orderFromCart(cartId, request.quantity());
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

}
