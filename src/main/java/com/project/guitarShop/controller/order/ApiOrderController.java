package com.project.guitarShop.controller.order;

import com.project.guitarShop.domain.order.Order;
import com.project.guitarShop.dto.order.OrderRequest.*;
import com.project.guitarShop.exception.NotEnoughStockException;
import com.project.guitarShop.exception.NotFoundMemberException;
import com.project.guitarShop.exception.item.NotFoundItemException;
import com.project.guitarShop.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiOrderController {

    private final OrderService orderService;

    @PostMapping("/{memberId}/{itemId}/order")
    public ResponseEntity<?> order(@PathVariable Long memberId, @PathVariable Long itemId, @RequestBody CreateOrderRequest request) {
        try {
            Order order = orderService.order(memberId, itemId, request.getCount());
            return ResponseEntity.status(HttpStatus.CREATED).body(order);
        } catch (NotFoundMemberException | NotFoundItemException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (NotEnoughStockException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
