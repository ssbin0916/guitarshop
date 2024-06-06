package com.project.guitarShop.service.order;


import com.project.guitarShop.entity.item.Item;
import com.project.guitarShop.entity.item.Category;
import com.project.guitarShop.entity.item.Brand;
import com.project.guitarShop.entity.order.Order;
import com.project.guitarShop.entity.order.OrderStatus;
import com.project.guitarShop.entity.orderItem.OrderItem;
import com.project.guitarShop.entity.address.Address;
import com.project.guitarShop.entity.delivery.Delivery;
import com.project.guitarShop.entity.delivery.DeliveryStatus;

import com.project.guitarShop.dto.member.MemberRequest;
import com.project.guitarShop.dto.member.MemberRequest.*;
import com.project.guitarShop.dto.member.MemberResponse.*;
import com.project.guitarShop.dto.item.ItemRequest.*;
import com.project.guitarShop.dto.item.ItemResponse.*;

import com.project.guitarShop.repository.item.ItemRepository;
import com.project.guitarShop.repository.order.OrderRepository;

import com.project.guitarShop.service.member.MemberServiceImpl;
import com.project.guitarShop.service.item.ItemServiceImpl;

import com.project.guitarShop.exception.item.NotFoundItemException;
import com.project.guitarShop.exception.order.NotFoundOrderException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
//@Rollback(value = false)
class OrderServiceTest {

    @Autowired
    MemberServiceImpl memberService;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ItemServiceImpl itemService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderService orderService;

    @Test
    void order() {
        //given
        JoinRequest memberRequest = createDummyJoinRequest();
        JoinResponse memberResponse = memberService.join(memberRequest);

        AddItemRequest itemRequest = createDummyAddItemRequest();
        AddItemResponse itemResponse = itemService.save(itemRequest);

        //when
        Order order = orderService.order();

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
        JoinRequest memberRequest = createDummyJoinRequest();
        JoinResponse memberResponse = memberService.join(memberRequest);

        AddItemRequest itemRequest = createDummyAddItemRequest();
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
        JoinRequest memberRequest = createDummyJoinRequest();
        memberService.join(memberRequest);

        AddItemRequest itemRequest = createDummyAddItemRequest();
        itemService.save(itemRequest);

        //when //then
        assertThrows(NotEnoughStockException.class, () -> {
            orderService.order(1L, 1L, 11);
        });
    }

    private JoinRequest createDummyJoinRequest() {
        return MemberRequest.JoinRequest.builder()
                .loginEmail("email@test.com")
                .password("password")
                .confirmPassword("password")
                .name("name")
                .phone("phone")
                .address(new Address("add", "re", "ss"))
                .build();
    }

    private AddItemRequest createDummyAddItemRequest() {
        return AddItemRequest.builder()
                .name("name")
                .price(10000)
                .quantity(10)
                .category(Category.ELECTRIC_GUITAR)
                .brand(Brand.JAMESTYLER)
                .build();
    }

    private LoginResponse loginMember(String username, String password) {
        return memberService.login(username, password);
    }
}
