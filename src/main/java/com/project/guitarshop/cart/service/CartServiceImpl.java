package com.project.guitarshop.cart.service;

import com.project.guitarshop.cart.entity.Cart;
import com.project.guitarshop.cart.entity.CartItem;
import com.project.guitarshop.item.entity.Item;
import com.project.guitarshop.member.entity.Member;
import com.project.guitarshop.cart.exception.NotFoundCartException;
import com.project.guitarshop.item.exception.NotFoundItemException;
import com.project.guitarshop.member.exception.NotFoundMemberException;
import com.project.guitarshop.cart.repository.CartRepository;
import com.project.guitarshop.item.repository.ItemRepository;
import com.project.guitarshop.member.repository.MemberRepository;
import com.project.guitarshop.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    @Override
    public Long addCart(Long itemId) {

        Long memberId = SecurityUtil.getCurrentUserId();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundMemberException("해당 회원을 찾을 수 없습니다."));

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundItemException("해당 상품을 찾을 수 없습니다."));

        CartItem cartItem = CartItem.createCartItem(item, item.getName(), item.getPrice());

        Cart cart = Cart.createCart(member, cartItem);

        cartRepository.save(cart);

        return cart.getId();
    }

    @Transactional
    @Override
    public void remove(Long cartId) {

        cartRepository.findById(cartId)
                .orElseThrow(() -> new NotFoundCartException("해당 장바구니를 찾을 수 없습니다."));

        cartRepository.deleteById(cartId);
    }

}
