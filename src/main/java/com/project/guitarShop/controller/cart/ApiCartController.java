package com.project.guitarShop.controller.cart;

import com.project.guitarShop.dto.cart.CartRequest.AddCartRequest;
import com.project.guitarShop.dto.cart.CartResponse.AddCartResponse;
import com.project.guitarShop.exception.NotFoundMemberException;
import com.project.guitarShop.exception.order.NotFoundOrderException;
import com.project.guitarShop.service.cart.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiCartController {

    private final CartService cartService;

    @PostMapping("{memberId}/{orderId}/addCart")
    public ResponseEntity<?> cart(@Valid @RequestBody AddCartRequest request) {

        try {
            AddCartResponse response = cartService.addCart(request);
            return ResponseEntity.ok(response);
        } catch (NotFoundMemberException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 회원을 찾을 수 없습니다.");
        } catch (NotFoundOrderException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 주문을 찾을 수 없습니다.");
        }
    }
}