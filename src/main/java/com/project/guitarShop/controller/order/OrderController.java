package com.project.guitarShop.controller.order;

import com.project.guitarShop.facade.LettuceLockFacade;
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
public class OrderController {

    private final LettuceLockFacade lettuceLockFacade;

    @PostMapping("/{memberId}/{itemId}/order")
    public ResponseEntity<Long> order(@PathVariable Long memberId, @PathVariable Long itemId) throws InterruptedException {
        Long orderId = lettuceLockFacade.order(memberId, itemId);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }

    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<String> cancel(@PathVariable Long orderId) throws InterruptedException {
        lettuceLockFacade.cancelOrder(orderId);
        return ResponseEntity.ok("주문이 취소되었습니다.");
    }

    @PostMapping("/{cartId}/orderFromCart")
    public ResponseEntity<Long> orderFromCart(@PathVariable Long cartId) throws InterruptedException {
        Long orderId = lettuceLockFacade.orderFromCart(cartId);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }

}
