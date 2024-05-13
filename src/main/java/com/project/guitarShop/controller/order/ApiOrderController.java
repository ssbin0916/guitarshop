package com.project.guitarShop.controller.order;

import com.project.guitarShop.dto.order.OrderRequest.*;
import com.project.guitarShop.dto.order.OrderResponse.*;
import com.project.guitarShop.exception.NotEnoughStockException;
import com.project.guitarShop.exception.NotFoundMemberException;
import com.project.guitarShop.exception.cart.NotFoundCartException;
import com.project.guitarShop.exception.item.NotFoundItemException;
import com.project.guitarShop.exception.order.NotFoundOrderException;
import com.project.guitarShop.service.order.OrderService;
import jakarta.validation.Valid;
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
    public ResponseEntity<?> order(@Valid @RequestBody CreateOrderRequest request) {

        try {
            CreateOrdersResponse response = orderService.order(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (NotFoundMemberException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 회원을 찾을 수 없습니다.");
        } catch (NotFoundItemException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 상품을 찾을 수 없습니다.");
        } catch (NotEnoughStockException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("재고가 부족합니다.");
        }
    }

    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<String> cancel(@PathVariable Long orderId) {

        try {
            orderService.cancelOrder(orderId);
            return ResponseEntity.ok("주문이 취소되었습니다.");
        } catch (NotFoundOrderException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 주문을 찾을 수 없습니다.");
        }
    }

    @PostMapping("/{cartId}/{cartItemId}/orderFromCart")
    public ResponseEntity<?> orderFormCart(@PathVariable Long cartId, @PathVariable Long cartItemId, @Valid @RequestBody OrderFormCartRequest request) {

        try {
            OrderFormCartResponse response = orderService.orderFromCart(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (NotFoundCartException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("카트에 상품이 없습니다.");
        } catch (NotFoundMemberException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 회원을 찾을 수 없습니다.");
        } catch (NotFoundItemException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 상품을 찾을 수 없습니다.");
        }
//        catch (NotEnoughStockException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("재고가 부족합니다.");
//        }
    }
}