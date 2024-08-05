package com.project.guitarshop.controller.cart;

import com.project.guitarshop.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/{itemId}")
    public ResponseEntity<Long> addCart(@PathVariable Long itemId) {
        Long addCart = cartService.addCart(itemId);
        return ResponseEntity.status(HttpStatus.CREATED).body(addCart);
    }

    @DeleteMapping("/remove/{cartId}")
    public ResponseEntity<Void> remove(@PathVariable Long cartId) {
        cartService.remove(cartId);
        return ResponseEntity.noContent().build();
    }

}
