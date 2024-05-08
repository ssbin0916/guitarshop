package com.project.guitarShop.service.order;


import com.project.guitarShop.domain.item.Item;
import com.project.guitarShop.domain.item.Category;
import com.project.guitarShop.domain.item.Brand;
import com.project.guitarShop.domain.order.Order;
import com.project.guitarShop.domain.order.OrderStatus;
import com.project.guitarShop.domain.orderItem.OrderItem;
import com.project.guitarShop.domain.address.Address;
import com.project.guitarShop.domain.delivery.Delivery;
import com.project.guitarShop.domain.delivery.DeliveryStatus;

import com.project.guitarShop.dto.member.MemberRequest;
import com.project.guitarShop.dto.member.MemberRequest.*;
import com.project.guitarShop.dto.member.MemberResponse.*;
import com.project.guitarShop.dto.item.ItemRequest.*;
import com.project.guitarShop.dto.item.ItemResponse.*;

import com.project.guitarShop.repository.item.ItemRepository;
import com.project.guitarShop.repository.order.OrderRepository;

import com.project.guitarShop.service.member.MemberService;
import com.project.guitarShop.service.item.ItemService;

import com.project.guitarShop.exception.NotEnoughStockException;
import com.project.guitarShop.exception.item.NotFoundItemException;
import com.project.guitarShop.exception.order.NotFoundOrderException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
//@Rollback(value = false)
class OrderServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ItemService itemService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderService orderService;

    @Test
    void order() {
        //given
        String male = "000000-1111111";

        JoinRequest memberRequest = MemberRequest.JoinRequest.builder()
                .loginId("loginId1")
                .password("password")
                .confirmPassword("password")
                .name("name")
                .rrn(male)
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

        //when
        Order order = orderService.order(memberResponse.getId(), itemResponse.getId(), 2);

        Optional<Order> save = orderRepository.findById(order.getId());
        assertTrue(save.isPresent());
        Order saveOrder = save.get();
        Delivery delivery = saveOrder.getDelivery();
        OrderItem orderItem = saveOrder.getOrderItems().get(0);

        //then
        assertEquals(memberRequest.getAddress(), delivery.getAddress());
        assertEquals(OrderStatus.ORDER, saveOrder.getOrderStatus(), "상품 주문시 주문 상태는 ORDER");
        assertEquals(DeliveryStatus.READY, delivery.getStatus(), "상품 주문시 배송 상태는 READY");
        assertEquals(1, saveOrder.getOrderItems().size(), "주문한 상품 종류 수가 정확해야 한다.");
        assertEquals(itemRequest.getPrice() * 2, orderItem.getTotalPrice(), "주문 가격은 가격 * 수량이다.");
    }

    @Test
    void cancelOrder() {
        //given
        String male = "000000-1111111";

        JoinRequest memberRequest = MemberRequest.JoinRequest.builder()
                .loginId("loginId1")
                .password("password")
                .confirmPassword("password")
                .name("name")
                .rrn(male)
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

        //when
        Order order = orderService.order(memberResponse.getId(), itemResponse.getId(), 2);
        orderService.cancelOrder(order.getId());

        //then
        Order save = orderRepository.findById(order.getId())
                .orElseThrow(() -> new NotFoundOrderException("주문 정보가 없습니다."));
        Item item = itemRepository.findById(itemResponse.getId())
                .orElseThrow(() -> new NotFoundItemException("아이템이 없습니다."));

        assertEquals(OrderStatus.CANCEL, save.getOrderStatus());
        assertEquals(10, item.getQuantity(), "주문 취소로 인해 재고가 복구되어야 한다.");
    }

    @Test
    void notEnoughStock() {
        String male = "000000-1111111";

        JoinRequest memberRequest = MemberRequest.JoinRequest.builder()
                .loginId("loginId1")
                .password("password")
                .confirmPassword("password")
                .name("name")
                .rrn(male)
                .phone("phone")
                .email("email")
                .address(new Address("add", "re", "ss"))
                .build();

        memberService.join(memberRequest);

        AddItemRequest itemRequest = AddItemRequest.builder()
                .name("name")
                .price(10000)
                .quantity(10)
                .category(Category.ELECTRIC_GUITAR)
                .brand(Brand.JAMESTYLER)
                .build();

        itemService.save(itemRequest);

        //when //then
        assertThrows(NotEnoughStockException.class, () -> {
            orderService.order(1L, 1L, 11);
        });
    }
}
