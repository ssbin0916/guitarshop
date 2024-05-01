package com.project.guitarShop.order.app;

import com.project.guitarShop.address.domain.Address;
import com.project.guitarShop.delivery.domain.Delivery;
import com.project.guitarShop.delivery.domain.DeliveryStatus;
import com.project.guitarShop.exception.NotEnoughStockException;
import com.project.guitarShop.item.app.ItemRequest;
import com.project.guitarShop.item.app.ItemServiceImpl;
import com.project.guitarShop.item.domain.Brand;
import com.project.guitarShop.item.domain.Category;
import com.project.guitarShop.item.domain.Item;
import com.project.guitarShop.item.repository.ItemRepository;
import com.project.guitarShop.member.app.MemberRequest;
import com.project.guitarShop.member.app.MemberServiceImpl;
import com.project.guitarShop.member.repository.MemberRepository;
import com.project.guitarShop.order.domain.Order;
import com.project.guitarShop.order.domain.OrderStatus;
import com.project.guitarShop.order.repository.OrderRepository;
import com.project.guitarShop.orderItem.domain.OrderItem;
import jakarta.persistence.EntityManager;
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
class OrderServiceImplTest {

    @Autowired
    EntityManager em;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberServiceImpl memberService;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ItemServiceImpl itemService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    void order() {
        //given
        MemberRequest memberRequest = new MemberRequest(
                "loginId",
                "password",
                "password",
                "name",
                29,
                "phone",
                "email",
                "ROLE_USER",
                new Address("addr1", "addr2", "addr3"),
                new ArrayList<Order>()
        );
        memberService.join(memberRequest);

        ItemRequest itemRequest = new ItemRequest("name", 10000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);
        itemService.save(itemRequest);

        //when
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, itemRepository, orderRepository);
        Long orderId = orderService.order(1L, 1L, 2);

        Optional<Order> savedOrder = orderRepository.findById(orderId);
        Delivery delivery = savedOrder.get().getDelivery();
        OrderItem orderItem = savedOrder.get().getOrderItems().get(0);

        //then
        assertEquals(memberRequest.address(), delivery.getAddress());
        assertEquals(OrderStatus.ORDER, savedOrder.get().getOrderStatus(), "상품 주문시 주문 상태는 ORDER");
        assertEquals(DeliveryStatus.READY, delivery.getStatus(), "상품 주문시 배송 상태는 READY");
        assertEquals(1, savedOrder.get().getOrderItems().size(), "주문한 상품 종류 수가 정확해야 한다.");
        assertEquals(itemRequest.price() * 2, orderItem.getTotalPrice(), "주문 가격은 가격 * 수량이다.");
    }

    @Test
    void cancelOrder() {
        //given
        MemberRequest memberRequest = new MemberRequest(
                "loginId",
                "password",
                "password",
                "name",
                29,
                "phone",
                "email",
                "ROLE_USER",
                new Address("addr1", "addr2", "addr3"),
                new ArrayList<Order>()
        );
        memberService.join(memberRequest);

        ItemRequest itemRequest = new ItemRequest("name", 10000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);
        itemService.save(itemRequest);

        //when
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, itemRepository, orderRepository);
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
        MemberRequest memberRequest = new MemberRequest(
                "loginId",
                "password",
                "password",
                "name",
                29,
                "phone",
                "email",
                "ROLE_USER",
                new Address("addr1", "addr2", "addr3"),
                new ArrayList<Order>()
        );
        memberService.join(memberRequest);

        ItemRequest itemRequest = new ItemRequest("name", 10000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);
        itemService.save(itemRequest);

        //when //then
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, itemRepository, orderRepository);
        assertThrows(NotEnoughStockException.class, () -> {
            orderService.order(1L, 1L, 11);
        });
    }

}
