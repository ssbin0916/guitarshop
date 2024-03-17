package com.project.guitarShop.web.cart;

import com.project.guitarShop.domain.cart.Cart;
import com.project.guitarShop.domain.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping("")
    public List<Cart> findByMemberId(@PathVariable("memberId") Long memberId) {
        return cartService.findByMemberId(memberId);
    }

    @PostMapping("")
    public void save(@RequestBody Cart cart) {
        cartService.save(cart);
    }

    @PutMapping("")
    public void updateQuantity(@RequestBody Cart cart) {
        cartService.updateQuantity(cart);
    }

    @DeleteMapping("")
    public void delete(@PathVariable("id") Long id) {
        cartService.delete(id);
    }
}
