package com.project.guitarShop.service.order;

import com.project.guitarShop.dto.order.OrderResponse.*;
import com.project.guitarShop.entity.cart.Cart;
import com.project.guitarShop.entity.cartItem.CartItem;
import com.project.guitarShop.entity.delivery.Delivery;
import com.project.guitarShop.entity.delivery.DeliveryStatus;
import com.project.guitarShop.entity.item.Item;
import com.project.guitarShop.entity.member.Member;
import com.project.guitarShop.entity.order.Order;
import com.project.guitarShop.entity.order.OrderStatus;
import com.project.guitarShop.entity.orderItem.OrderItem;
import com.project.guitarShop.exception.RedissonLockFailedException;
import com.project.guitarShop.exception.cart.NotFoundCartException;
import com.project.guitarShop.exception.item.NotEnoughStockException;
import com.project.guitarShop.exception.item.NotFoundItemException;
import com.project.guitarShop.exception.member.NotFoundMemberException;
import com.project.guitarShop.exception.order.NotFoundOrderException;
import com.project.guitarShop.exception.order.OrderCancelException;
import com.project.guitarShop.repository.cart.CartRepository;
import com.project.guitarShop.repository.item.ItemRepository;
import com.project.guitarShop.repository.member.MemberRepository;
import com.project.guitarShop.repository.order.OrderRepository;
import com.project.guitarShop.repository.redis.RedisRepository;
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
    public CreateOrderResponse order(Long memberId, Long itemId, Integer quantity) throws InterruptedException {

        RLock lock = redissonClient.getLock(itemId.toString());

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
    public void cancel(Long id) throws InterruptedException {

        RLock lock = redissonClient.getLock(id.toString());

        Order order = orderRepository.findById(id)
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

        Member member = memberRepository.findById(cart.getMember().getId())
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