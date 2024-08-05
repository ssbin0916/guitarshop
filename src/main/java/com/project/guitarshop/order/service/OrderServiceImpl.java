package com.project.guitarshop.order.service;

import com.project.guitarshop.order.dto.OrderResponse.CreateOrderFromCartResponse;
import com.project.guitarshop.order.dto.OrderResponse.CreateOrderResponse;
import com.project.guitarshop.cart.entity.Cart;
import com.project.guitarshop.cart.entity.CartItem;
import com.project.guitarshop.order.entity.Delivery;
import com.project.guitarshop.order.entity.DeliveryStatus;
import com.project.guitarshop.item.entity.Item;
import com.project.guitarshop.member.entity.Member;
import com.project.guitarshop.order.entity.Order;
import com.project.guitarshop.order.entity.OrderStatus;
import com.project.guitarshop.order.entity.OrderItem;
import com.project.guitarshop.infrastructure.exception.RedissonLockFailedException;
import com.project.guitarshop.cart.exception.NotFoundCartException;
import com.project.guitarshop.item.exception.NotEnoughStockException;
import com.project.guitarshop.item.exception.NotFoundItemException;
import com.project.guitarshop.member.exception.NotFoundMemberException;
import com.project.guitarshop.order.exception.NotFoundOrderException;
import com.project.guitarshop.order.exception.OrderCancelException;
import com.project.guitarshop.cart.repository.CartRepository;
import com.project.guitarshop.item.repository.ItemRepository;
import com.project.guitarshop.member.repository.MemberRepository;
import com.project.guitarshop.order.repository.OrderRepository;
import com.project.guitarshop.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final RedissonClient redissonClient;

    @Override
    @Transactional
    public CreateOrderResponse order(Long itemId, Integer quantity) throws InterruptedException {

        RLock lock = redissonClient.getLock(itemId.toString());

        Long memberId = SecurityUtil.getCurrentUserId();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundMemberException("해당 회원을 찾을 수 없습니다."));

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundItemException("해당 상품을 찾을 수 없습니다."));

        if (item.getQuantity() < quantity) {
            throw new NotEnoughStockException("재고가 부족합니다.");
        }

        Delivery delivery = Delivery.builder()
                .address(member.getAddress())
                .deliveryStatus(DeliveryStatus.READY)
                .build();

        Order order = new Order();

        try {
            boolean available = lock.tryLock(10, 1, TimeUnit.SECONDS);

            if (!available) {
                throw new RedissonLockFailedException("lock 획득 실패");
            }
            OrderItem orderItem = OrderItem.createOrderItem(item, item.getName(), item.getPrice(), quantity);
            Order.createOrder(member, delivery, orderItem);
            orderRepository.save(order);
        } finally {
            lock.unlock();
        }
        return new CreateOrderResponse(order);
    }

    @Override
    @Transactional
    public void cancel(Long orderId) throws InterruptedException {

        RLock lock = redissonClient.getLock(orderId.toString());

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundOrderException("해당 주문을 찾을 수 없습니다."));

        if (order.getOrderStatus() == OrderStatus.CANCEL) {
            throw new OrderCancelException("이미 취소된 주문입니다.");
        }

        if (order.getDelivery().getDeliveryStatus() == DeliveryStatus.DELIVERING || order.getDelivery().getDeliveryStatus() == DeliveryStatus.COMPLETE) {
            throw new OrderCancelException("배송된 주문은 취소할 수 없습니다.");
        }

        try {
            boolean available = lock.tryLock(10, 1, TimeUnit.SECONDS);

            if (!available) {
                throw new RedissonLockFailedException("lock 획득 실패");
            }
            order.cancel();
            orderRepository.save(order);
        } finally {
            lock.unlock();
        }
    }

    @Override
    @Transactional
    public CreateOrderFromCartResponse orderFromCart(Long cartId, Integer quantity) throws InterruptedException {

        RLock lock = redissonClient.getLock(cartId.toString());

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new NotFoundCartException("장바구니를 찾을 수 없습니다."));

        Long memberId = SecurityUtil.getCurrentUserId();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundMemberException("해당 회원을 찾을 수 없습니다."));

        Delivery delivery = Delivery.builder()
                .address(member.getAddress())
                .deliveryStatus(DeliveryStatus.READY)
                .build();


        List<CartItem> cartItems = cart.getCartItems();
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = OrderItem.createOrderItem(cartItem.getItem(), cartItem.getName(), cartItem.getPrice(), quantity);
            orderItems.add(orderItem);
        }

        Order order = new Order();

        try {
            boolean available = lock.tryLock(10, 1, TimeUnit.SECONDS);

            if (!available) {
                throw new RedissonLockFailedException("lock 획득 실패");
            }

            Order.createOrder(member, delivery, orderItems.toArray(new OrderItem[0]));
            orderRepository.save(order);
        } finally {
            lock.unlock();
        }
        return new CreateOrderFromCartResponse(order);
    }
}