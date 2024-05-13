package com.project.guitarShop.controller.cart;

import com.project.guitarShop.dto.cart.CartRequest.AddCartRequest;
import com.project.guitarShop.dto.cart.CartResponse.AddCartResponse;
import com.project.guitarShop.exception.NotEnoughStockException;
import com.project.guitarShop.exception.NotFoundMemberException;
import com.project.guitarShop.exception.cart.NotFoundCartException;
import com.project.guitarShop.exception.item.NotFoundItemException;
import com.project.guitarShop.service.cart.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiCartController {

    private final CartService cartService;

    @PostMapping("/{memberId}/{itemId}/addCart")
    public ResponseEntity<?> cart(@Valid @RequestBody AddCartRequest request) {

        try {
            AddCartResponse response = cartService.addCart(request);
            return ResponseEntity.ok(response);
        } catch (NotFoundMemberException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 회원을 찾을 수 없습니다.");
        } catch (NotFoundItemException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 상품을 찾을 수 없습니다.");
        } catch (NotEnoughStockException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("재고가 부족합니다.");
        }
    }

    @PostMapping("/{cartId}/remove")
    public ResponseEntity<?> remove(@PathVariable Long cartId) {

        try {
            cartService.remove(cartId);
            return ResponseEntity.ok("장바구니에 담긴 상품이 모두 삭제되었습니다.");
        } catch (NotFoundCartException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 장바구니를 찾을 수 없습니다.");
        }
    }
}
