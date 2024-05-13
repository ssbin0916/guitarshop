package com.project.guitarShop.service.order;

import com.project.guitarShop.entity.address.Address;
import com.project.guitarShop.entity.delivery.Delivery;
import com.project.guitarShop.entity.delivery.DeliveryStatus;
import com.project.guitarShop.entity.item.Item;
import com.project.guitarShop.entity.member.Member;
import com.project.guitarShop.entity.order.Order;
import com.project.guitarShop.entity.order.OrderStatus;
import com.project.guitarShop.entity.orderItem.OrderItem;
import com.project.guitarShop.dto.order.OrderRequest.*;
import com.project.guitarShop.dto.order.OrderResponse.*;
import com.project.guitarShop.exception.NotEnoughStockException;
import com.project.guitarShop.exception.NotFoundMemberException;
import com.project.guitarShop.exception.item.NotFoundItemException;
import com.project.guitarShop.exception.order.NotFoundOrderException;
import com.project.guitarShop.exception.order.OrderCancelException;
import com.project.guitarShop.repository.cart.CartRepository;
import com.project.guitarShop.repository.item.ItemRepository;
import com.project.guitarShop.repository.member.MemberRepository;
import com.project.guitarShop.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    public CreateOrdersResponse order(CreateOrderRequest request) {

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new NotFoundMemberException("해당 회원을 찾을 수 없습니다."));

        Item item = itemRepository.findById(request.getItemId())
                .orElseThrow(() -> new NotFoundItemException("해당 상품을 찾을 수 없습니다."));

        if (item.getQuantity() < request.getQuantity()) {
            throw new NotEnoughStockException("상품의 재고가 부족합니다.");
        }

        Address deliveryAddress = (request.getDeliveryAddress() != null) ? request.getDeliveryAddress() : member.getAddress();
        Delivery delivery = new Delivery(deliveryAddress, DeliveryStatus.READY);

        OrderItem orderItem = OrderItem.builder()
                .item(item)
                .orderPrice(item.getPrice())
                .quantity(request.getQuantity())
                .build();

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);

        Order order = Order.builder()
                .member(member)
                .orderItems(orderItems)
                .delivery(delivery)
                .orderDate(LocalDateTime.now())
                .orderStatus(OrderStatus.ORDER)
                .build();


        orderRepository.save(order);
        item.removeStock(request.getQuantity());
        itemRepository.save(item);

        return new CreateOrdersResponse(order);
    }


    public void cancelOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundOrderException("해당 주문을 찾을 수 없습니다."));

        if (order.getOrderStatus() == OrderStatus.CANCEL) {
            throw new OrderCancelException("이미 취소된 주문입니다.");
        }

        if (order.getDelivery().getStatus() == DeliveryStatus.DELIVERING || order.getDelivery().getStatus() == DeliveryStatus.COMPLETE) {
            throw new OrderCancelException("배송된 주문은 취소할 수 없습니다.");
        }

        order.cancel();

        orderRepository.save(order);
    }

//    public OrderFormCartResponse orderFromCart(OrderFormCartRequest request) {
//
//        Cart cart = cartRepository.findById(request.getCartId())
//                .orElseThrow(() -> new NotFoundCartException("장바구니가 비어있습니다."));
//
//        Member member = memberRepository.findById(request.getMemberId())
//                .orElseThrow(() -> new NotFoundMemberException("해당 회원을 찾을 수 없습니다."));
//
//        Item item = itemRepository.findById(request.getItemId())
//                .orElseThrow(() -> new NotFoundItemException("해당 상품을 찾을 수 없습니다."));
//
//        if (cart.getOrderItems().isEmpty()) {
//            throw new NotFoundCartException("장바구니에 주문할 상품이 없습니다.");
//        }
//
//        Address deliveryAddress = (request.getDeliveryAddress() != null ? request.getDeliveryAddress() : member.getAddress());
//        Delivery delivery = new Delivery(deliveryAddress, DeliveryStatus.READY);
//
//        OrderItem orderItem = OrderItem.builder()
//                .item(item)
//                .orderPrice(item.getPrice())
//                .quantity(request.getQuantity())
//                .build();
//
//        List<OrderItem> orderItems = new ArrayList<>();
//        orderItems.add(orderItem);
//
//        Order order = Order.builder()
//                .member(member)
//                .orderItems(orderItems)
//                .delivery(delivery)
//                .orderDate(LocalDateTime.now())
//                .orderStatus(OrderStatus.ORDER)
//                .build();
//
//        orderRepository.save(order);
//        item.removeStock(request.getQuantity());
//        itemRepository.save(item);
//
//        return new OrderFormCartResponse(order);
//    }
}
