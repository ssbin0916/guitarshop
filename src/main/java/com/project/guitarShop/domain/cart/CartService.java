package com.project.guitarShop.domain.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartMapper cartMapper;

    @Transactional(readOnly = true)
    public List<Cart> findByMemberId(Long memberId) {
        return cartMapper.findByMemberId(memberId);
    }

    @Transactional
    public void save(Cart cart) {
        cartMapper.save(cart);
    }

    @Transactional
    public void updateQuantity(Cart cart) {
        cartMapper.updateQuantity(cart);
    }

    @Transactional
    public void delete(Long id) {
        cartMapper.delete(id);
    }
}
