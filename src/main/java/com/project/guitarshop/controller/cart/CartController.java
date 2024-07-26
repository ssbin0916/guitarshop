package com.project.guitarshop.controller.cart;

import com.project.guitarshop.service.cart.CartService;
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
public class CartController {

    private final CartService cartService;

    @PostMapping("/{memberId}/{itemId}/addCart")
    public ResponseEntity<Long> addCart(@PathVariable Long memberId, @PathVariable Long itemId) {
        Long addCart = cartService.addCart(memberId, itemId);
        return ResponseEntity.status(HttpStatus.CREATED).body(addCart);
    }

    @PostMapping("/{cartId}/remove")
    public ResponseEntity<?> remove(@PathVariable Long cartId) {
        cartService.remove(cartId);
        return ResponseEntity.ok("장바구니에 담긴 상품이 모두 삭제되었습니다.");
    }

}
