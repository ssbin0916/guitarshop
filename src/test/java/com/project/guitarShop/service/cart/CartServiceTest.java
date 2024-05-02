package com.project.guitarShop.service.cart;

import com.project.guitarShop.domain.cart.Cart;
import com.project.guitarShop.repository.cart.CartRepository;
import com.project.guitarShop.dto.item.ItemRequest;
import com.project.guitarShop.service.item.ItemService;
import com.project.guitarShop.domain.item.Brand;
import com.project.guitarShop.domain.item.Category;
import com.project.guitarShop.repository.item.ItemRepository;
import com.project.guitarShop.service.member.MemberService;
import com.project.guitarShop.domain.member.Member;
import com.project.guitarShop.repository.member.MemberRepository;
import com.project.guitarShop.service.order.OrderService;
import com.project.guitarShop.repository.order.OrderRepository;
import com.project.guitarShop.repository.orderItem.OrderItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
//@Rollback(value = false)
class CartServiceTest {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartServiceImpl cartService;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    ItemService itemService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    OrderRepository orderRepository;

    @Test
    void addCart() {
        //given
        Member member = new Member();
        Cart cart = cartService.crea
        memberService.join(memberRequest);
        ItemRequest itemRequest = new ItemRequest("name", 10000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);
        itemService.save(itemRequest);

        //when
        OrderService orderService = new OrderService(memberRepository, itemRepository, orderRepository);

        //when
        Cart savedCart = cartService.addCart(1L, orderItem);

        // Then: 검증
        assertNotNull(savedCart);
        assertEquals(1, savedCart.getOrderItems().size());
        assertTrue(savedCart.getOrderItems().contains(orderItem));
    }

    public Cart createCart(Member member) {
        // 회원 저장
        memberService.join(member);

        // 회원 id를 확인하여 Cart 생성
        Long memberId = member.getId();
        if (memberId == null) {
            throw new IllegalArgumentException("회원 ID가 null입니다.");
        }

        Cart cart = Cart.builder()
                .member(member)
                .orderItems(new ArrayList<>())
                .build();
        return cartRepository.save(cart);
    }
}