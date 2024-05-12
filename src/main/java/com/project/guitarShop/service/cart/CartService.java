package com.project.guitarShop.service.cart;

import com.project.guitarShop.domain.cart.Cart;
import com.project.guitarShop.domain.member.Member;
import com.project.guitarShop.domain.order.Order;
import com.project.guitarShop.dto.cart.CartRequest.AddCartRequest;
import com.project.guitarShop.dto.cart.CartResponse.AddCartResponse;
import com.project.guitarShop.exception.NotFoundMemberException;
import com.project.guitarShop.exception.cart.NotFoundCartException;
import com.project.guitarShop.exception.order.NotFoundOrderException;
import com.project.guitarShop.repository.cart.CartRepository;
import com.project.guitarShop.repository.member.MemberRepository;
import com.project.guitarShop.repository.order.OrderRepository;
import com.project.guitarShop.repository.orderItem.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public AddCartResponse addCart(AddCartRequest request) {

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new NotFoundMemberException("해당 회원을 찾을 수 없습니다."));

        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new NotFoundOrderException("해당 주문을 찾을 수 없습니다."));

        Cart cart = Cart.builder()
                .member(member)
                .order(order)
                .orderItems(request.getOrderItems())
                .build();

        Cart save = cartRepository.save(cart);

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
