package com.project.guitarShop.service.order;

import com.project.guitarShop.domain.address.Address;
import com.project.guitarShop.domain.delivery.Delivery;
import com.project.guitarShop.domain.delivery.DeliveryStatus;
import com.project.guitarShop.exception.NotEnoughStockException;
import com.project.guitarShop.dto.item.ItemRequest;
import com.project.guitarShop.service.item.ItemService;
import com.project.guitarShop.domain.item.Brand;
import com.project.guitarShop.domain.item.Category;
import com.project.guitarShop.domain.item.Item;
import com.project.guitarShop.repository.item.ItemRepository;
import com.project.guitarShop.dto.member.MemberRequest;
import com.project.guitarShop.service.member.MemberService;
import com.project.guitarShop.repository.member.MemberRepository;
import com.project.guitarShop.domain.order.Order;
import com.project.guitarShop.domain.order.OrderStatus;
import com.project.guitarShop.repository.order.OrderRepository;
import com.project.guitarShop.domain.orderItem.OrderItem;
import com.project.guitarShop.service.order.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
//@Rollback(value = false)
class OrderServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ItemService itemService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    void order() {
        //given
        MemberRequest memberRequest = MemberRequest.builder()
                .loginId("loginId")
                .password("password")
                .confirmPassword("password")
                .name("name")
                .age(29)
                .phone("phone")
                .email("email")
                .role("ADMIN")
                .address(new Address("add", "re", "ss"))
                .orders(new ArrayList<>())
                .build();
        memberService.join(memberRequest);

        ItemRequest itemRequest = new ItemRequest("name", 10000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);
        itemService.save(itemRequest);

        //when
        OrderService orderService = new OrderService(memberRepository, itemRepository, orderRepository);
        Long orderId = orderService.order(1L, 1L, 2);

        Optional<Order> savedOrder = orderRepository.findById(orderId);
        Delivery delivery = savedOrder.get().getDelivery();
        OrderItem orderItem = savedOrder.get().getOrderItems().get(0);

        //then
        assertEquals(memberRequest.getAddress(), delivery.getAddress());
        assertEquals(OrderStatus.ORDER, savedOrder.get().getOrderStatus(), "상품 주문시 주문 상태는 ORDER");
        assertEquals(DeliveryStatus.READY, delivery.getStatus(), "상품 주문시 배송 상태는 READY");
        assertEquals(1, savedOrder.get().getOrderItems().size(), "주문한 상품 종류 수가 정확해야 한다.");
        assertEquals(itemRequest.price() * 2, orderItem.getTotalPrice(), "주문 가격은 가격 * 수량이다.");
    }

    @Test
    void cancelOrder() {
        //given
        MemberRequest memberRequest = MemberRequest.builder()
                .loginId("loginId")
                .password("password")
                .confirmPassword("password")
                .name("name")
                .age(29)
                .phone("phone")
                .email("email")
                .role("ADMIN")
                .address(new Address("add", "re", "ss"))
                .build();
        memberService.join(memberRequest);

        ItemRequest itemRequest = new ItemRequest("name", 10000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);
        itemService.save(itemRequest);

        //when
        OrderService orderService = new OrderService(memberRepository, itemRepository, orderRepository);
        Long orderId = orderService.order(1L, 1L, 2);
        orderService.cancelOrder(orderId);

        //then
        Optional<Order> saveOrder = orderRepository.findById(orderId);
        Optional<Item> saveItem = itemRepository.findById(1L);
        assertEquals(OrderStatus.CANCEL, saveOrder.get().getOrderStatus());
        assertTrue(saveItem.isPresent(), "상품 정보가 존재해야 한다.");
        assertEquals(10, saveItem.get().getQuantity(), "주문 취소로 인해 재고가 복구되어야 한다.");
    }

    @Test
    void notEnoughStock() {
        //given
        MemberRequest memberRequest = MemberRequest.builder()
                .loginId("loginId")
                .password("password")
                .confirmPassword("password")
                .name("name")
                .age(29)
                .phone("phone")
                .email("email")
                .role("ADMIN")
                .address(new Address("add", "re", "ss"))
                .build();
        memberService.join(memberRequest);

        ItemRequest itemRequest = new ItemRequest("name", 10000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);
        itemService.save(itemRequest);

        //when //then
        OrderService orderService = new OrderService(memberRepository, itemRepository, orderRepository);
        assertThrows(NotEnoughStockException.class, () -> {
            orderService.order(1L, 1L, 11);
        });
    }

}
