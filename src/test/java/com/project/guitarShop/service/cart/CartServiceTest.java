package com.project.guitarShop.service.cart;

import com.project.guitarShop.domain.address.Address;
import com.project.guitarShop.domain.cart.Cart;
import com.project.guitarShop.domain.item.Brand;
import com.project.guitarShop.domain.item.Category;
import com.project.guitarShop.domain.order.Order;
import com.project.guitarShop.dto.item.ItemRequest.*;
import com.project.guitarShop.dto.item.ItemResponse.*;
import com.project.guitarShop.dto.member.MemberRequest.*;
import com.project.guitarShop.dto.member.MemberResponse.*;
import com.project.guitarShop.service.item.ItemService;
import com.project.guitarShop.service.member.MemberService;
import com.project.guitarShop.service.order.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
//@Rollback(value = false)
class CartServiceTest {

    @Autowired
    CartService cartService;
    @Autowired
    MemberService memberService;
    @Autowired
    ItemService itemService;
    @Autowired
    OrderService orderService;


    @Test
    void addCart() {
        //given
        JoinRequest memberRequest = JoinRequest.builder()
                .loginId("loginId1")
                .password("password")
                .confirmPassword("password")
                .name("name")
                .rrn("000000-1111111")
                .phone("phone")
                .email("email")
                .address(new Address("add", "re", "ss"))
                .build();

        JoinResponse memberResponse = memberService.join(memberRequest);

        AddItemRequest itemRequest = AddItemRequest.builder()
                .name("name")
                .price(10000)
                .quantity(10)
                .category(Category.ELECTRIC_GUITAR)
                .brand(Brand.JAMESTYLER)
                .build();

        AddItemResponse itemResponse = itemService.save(itemRequest);

        Order order = orderService.order(memberResponse.getId(), itemResponse.getId(), 10);

        //when
        Cart cart = cartService.addCart(memberResponse.getId(), order.getId(), order.getOrderItems());

        //then
        assertNotNull(cart);
        assertEquals(1, cart.getOrderItems().size());
        assertEquals(itemResponse.getId(), cart.getOrderItems().get(0).getId());
        assertEquals(1L, cart.getId());
    }

    @Test
    void remove() {
        //given
        JoinRequest memberRequest = JoinRequest.builder()
                .loginId("loginId1")
                .password("password")
                .confirmPassword("password")
                .name("name")
                .rrn("000000-1111111")
                .phone("phone")
                .email("email")
                .address(new Address("add", "re", "ss"))
                .build();

        JoinResponse memberResponse = memberService.join(memberRequest);

        AddItemRequest itemRequest = AddItemRequest.builder()
                .name("name")
                .price(10000)
                .quantity(10)
                .category(Category.ELECTRIC_GUITAR)
                .brand(Brand.JAMESTYLER)
                .build();

        AddItemResponse itemResponse = itemService.save(itemRequest);

        Order order = orderService.order(memberResponse.getId(), itemResponse.getId(), 10);

        Cart cart = cartService.addCart(memberResponse.getId(), order.getId(), order.getOrderItems());

        //when
        cartService.removeItemFromCart(cart.getId());

        //then
        assertEquals(10, order.getOrderItems().get(0).getQuantity());
    }


    @Test
    void update() {
        //given
        JoinRequest memberRequest = JoinRequest.builder()
                .loginId("loginId1")
                .password("password")
                .confirmPassword("password")
                .name("name")
                .rrn("000000-1111111")
                .phone("phone")
                .email("email")
                .address(new Address("add", "re", "ss"))
                .build();

        JoinResponse memberResponse = memberService.join(memberRequest);

        AddItemRequest itemRequest = AddItemRequest.builder()
                .name("name")
                .price(10000)
                .quantity(10)
                .category(Category.ELECTRIC_GUITAR)
                .brand(Brand.JAMESTYLER)
                .build();

        AddItemResponse itemResponse = itemService.save(itemRequest);

        Order order = orderService.order(memberResponse.getId(), itemResponse.getId(), 10);

        Cart cart = cartService.addCart(memberResponse.getId(), order.getId(), order.getOrderItems());

        int newQuantity = 3;

        //when
        cartService.updateItemQuantity(cart.getId(), cart.getOrderItems().get(0).getId(), newQuantity);

        //then
        assertEquals(newQuantity, cart.getOrderItems().get(0).getQuantity());
    }
}