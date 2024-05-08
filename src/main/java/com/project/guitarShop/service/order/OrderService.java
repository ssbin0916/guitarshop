package com.project.guitarShop.service.order;

import com.project.guitarShop.domain.cart.Cart;
import com.project.guitarShop.domain.delivery.Delivery;
import com.project.guitarShop.domain.delivery.DeliveryStatus;
import com.project.guitarShop.domain.item.Item;
import com.project.guitarShop.domain.member.Member;
import com.project.guitarShop.domain.order.Order;
import com.project.guitarShop.domain.order.OrderStatus;
import com.project.guitarShop.domain.orderItem.OrderItem;
import com.project.guitarShop.exception.NotEnoughStockException;
import com.project.guitarShop.exception.NotFoundMemberException;
import com.project.guitarShop.exception.cart.NotFoundCartException;
import com.project.guitarShop.exception.item.NotFoundItemException;
import com.project.guitarShop.exception.order.NotFoundOrderException;
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

    public Order order(Long memberId, Long itemId, int count) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundMemberException("해당 회원을 찾을 수 없습니다."));

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundItemException("해당 상품을 찾을 수 없습니다."));

        if (item.getQuantity() < count) {
            throw new NotEnoughStockException("상품의 재고가 부족합니다.");
        }

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);

        item.removeStock(count);
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);

        Order order = Order.createOrder(member, delivery, orderItems, LocalDateTime.now(), OrderStatus.ORDER);

        orderRepository.save(order);

        return order;
    }


    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundOrderException("해당 주문을 찾을 수 없습니다."));
        order.cancel();
        orderRepository.save(order);
    }

    public Long orderFromCart(Long memberId) {
        Cart cart = cartRepository.findByMemberId(memberId)
                .orElseThrow(() -> new NotFoundCartException("장바구니가 비어있습니다."));

        if (cart.getOrderItems().isEmpty()) {
            throw new NotFoundCartException("장바구니에 주문할 상품이 없습니다.");
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundMemberException("해당 회원을 찾을 수 없습니다."));
        Delivery delivery = new Delivery(member.getAddress());

        List<OrderItem> orderItems = new ArrayList<>(cart.getOrderItems());
        Order order = Order.createOrder(member, delivery, orderItems, LocalDateTime.now(), OrderStatus.ORDER);

        orderRepository.save(order);
        return order.getId();
    }
}
