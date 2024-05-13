package com.project.guitarShop.service.cart;

import com.project.guitarShop.entity.cart.Cart;
import com.project.guitarShop.entity.cartItem.CartItem;
import com.project.guitarShop.entity.item.Item;
import com.project.guitarShop.entity.member.Member;
import com.project.guitarShop.dto.cart.CartRequest.AddCartRequest;
import com.project.guitarShop.dto.cart.CartResponse.AddCartResponse;
import com.project.guitarShop.exception.NotEnoughStockException;
import com.project.guitarShop.exception.NotFoundMemberException;
import com.project.guitarShop.exception.cart.NotFoundCartException;
import com.project.guitarShop.exception.item.NotFoundItemException;
import com.project.guitarShop.repository.cart.CartRepository;
import com.project.guitarShop.repository.item.ItemRepository;
import com.project.guitarShop.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    public AddCartResponse addCart(AddCartRequest request) {

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new NotFoundMemberException("해당 회원을 찾을 수 없습니다."));

        Cart cart = cartRepository.findByMemberId(request.getMemberId())
                .orElseGet(() -> Cart.builder().member(member).build());

        Item item = itemRepository.findById(request.getItemId())
                .orElseThrow(() -> new NotFoundItemException("해당 상품을 찾을 수 없습니다."));

        if (item.getQuantity() < request.getQuantity()) {
            throw new NotEnoughStockException("상품 재고가 부족합니다.");
        }

        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .item(item)
                .quantity(request.getQuantity())
                .build();

        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);

        Cart save = Cart.builder()
                .member(member)
                .cartItems(cartItems)
                .build();

        return new AddCartResponse(save);
    }

    public void removeItemFromCart(Long cartId) {

        cartRepository.findById(cartId)
                .orElseThrow(() -> new NotFoundCartException("해당 장바구니를 찾을 수 없습니다."));

        cartRepository.deleteById(cartId);
    }

//    public Cart updateItemQuantity(Long cartId, Long orderItemId, int quantity) {
//
//        Cart cart = cartRepository.findById(cartId)
//                .orElseThrow(() -> new NotFoundCartException("해당 장바구니를 찾을 수 없습니다."));
//
//        OrderItem orderItem = cart.getOrderItems().stream()
//                .filter(item -> item.getId().equals(orderItemId))
//                .findFirst()
//                .orElseThrow(() -> new NotFoundItemException("해당 주문 상품을 찾을 수 없습니다."));
//
//        orderItem.setQuantity(quantity);
//        orderItemRepository.save(orderItem);
//        cartRepository.save(cart);
//
//        return cart;
//    }
}
