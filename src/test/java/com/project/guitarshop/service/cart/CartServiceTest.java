//package com.project.guitarshop.service.cart;
//
//import com.project.guitarshop.entity.address.Address;
//import com.project.guitarshop.entity.cart.Cart;
//import com.project.guitarshop.entity.item.Brand;
//import com.project.guitarshop.entity.item.Category;
//import com.project.guitarshop.entity.order.Order;
//import com.project.guitarshop.dto.item.ItemRequest.*;
//import com.project.guitarshop.dto.item.ItemResponse.*;
//import com.project.guitarshop.dto.member.MemberRequest;
//import com.project.guitarshop.dto.member.MemberRequest.*;
//import com.project.guitarshop.dto.member.MemberResponse.*;
//import com.project.guitarshop.service.item.ItemServiceImpl;
//import com.project.guitarshop.service.member.MemberServiceImpl;
//import com.project.guitarshop.service.order.OrderServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
////@Rollback(value = false)
//class CartServiceTest {
//
//    @Autowired
//    CartServiceImpl cartService;
//    @Autowired
//    MemberServiceImpl memberService;
//    @Autowired
//    ItemServiceImpl itemService;
//    @Autowired
//    OrderServiceImpl orderService;
//
//
//    @Test
//    void addCart() {
//        //given
//        JoinRequest memberRequest = createDummyJoinRequest();
//        JoinResponse memberResponse = memberService.join(memberRequest);
//
//        AddItemRequest itemRequest = createDummyAddItemRequest();
//        AddItemResponse itemResponse = itemService.save(itemRequest);
//
//        Order order = orderService.order(memberResponse.getId(), itemResponse.getId(), 10);
//
//        //when
//        Cart cart = cartService.addCart(memberResponse.getId(), order.getId(), order.getOrderItems());
//
//        //then
//        assertNotNull(cart);
//        assertEquals(1, cart.getOrderItems().size());
//        assertEquals(itemResponse.getId(), cart.getOrderItems().get(0).getId());
//        assertEquals(1L, cart.getId());
//    }
//
//    @Test
//    void remove() {
//        //given
//        JoinRequest memberRequest = createDummyJoinRequest();
//        JoinResponse memberResponse = memberService.join(memberRequest);
//
//        AddItemRequest itemRequest = createDummyAddItemRequest();
//        AddItemResponse itemResponse = itemService.save(itemRequest);
//
//        Order order = orderService.order(memberResponse.getId(), itemResponse.getId(), 10);
//
//        Cart cart = cartService.addCart(memberResponse.getId(), order.getId(), order.getOrderItems());
//
//        //when
//        cartService.removeItemFromCart(cart.getId());
//
//        //then
//        assertEquals(10, order.getOrderItems().get(0).getQuantity());
//    }
//
//
//    @Test
//    void update() {
//        //given
//        JoinRequest memberRequest = createDummyJoinRequest();
//        JoinResponse memberResponse = memberService.join(memberRequest);
//
//        AddItemRequest itemRequest = createDummyAddItemRequest();
//        AddItemResponse itemResponse = itemService.save(itemRequest);
//
//        Order order = orderService.order(memberResponse.getId(), itemResponse.getId(), 10);
//
//        Cart cart = cartService.addCart(memberResponse.getId(), order.getId(), order.getOrderItems());
//
//        int newQuantity = 3;
//
//        //when
//        cartService.updateItemQuantity(cart.getId(), cart.getOrderItems().get(0).getId(), newQuantity);
//
//        //then
//        assertEquals(newQuantity, cart.getOrderItems().get(0).getQuantity());
//    }
//
//    private JoinRequest createDummyJoinRequest() {
//        return MemberRequest.JoinRequest.builder()
//                .loginEmail("email@test.com")
//                .password("password")
//                .confirmPassword("password")
//                .name("name")
//                .phone("phone")
//                .address(new Address("add", "re", "ss"))
//                .build();
//    }
//
//    private AddItemRequest createDummyAddItemRequest() {
//        return AddItemRequest.builder()
//                .name("name")
//                .price(10000)
//                .quantity(10)
//                .category(Category.ELECTRIC_GUITAR)
//                .brand(Brand.JAMESTYLER)
//                .build();
//    }
//}