package com.project.guitarShop.service.order;

import com.project.guitarShop.domain.delivery.Delivery;
import com.project.guitarShop.domain.delivery.DeliveryStatus;
import com.project.guitarShop.exception.NotFoundItemException;
import com.project.guitarShop.exception.NotFoundMemberException;
import com.project.guitarShop.domain.item.Item;
import com.project.guitarShop.repository.item.ItemRepository;
import com.project.guitarShop.domain.member.Member;
import com.project.guitarShop.repository.member.MemberRepository;
import com.project.guitarShop.domain.order.Order;
import com.project.guitarShop.repository.order.OrderRepository;
import com.project.guitarShop.domain.orderItem.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    @Override
    public Long order(Long memberId, Long itemId, int count) {

        Optional<Member> member = Optional.ofNullable(memberRepository.findById(memberId).orElseThrow(() -> new NotFoundMemberException("존재하지 않는 회원입니다.")));

        Optional<Item> item = Optional.ofNullable(itemRepository.findById(itemId).orElseThrow(() -> new NotFoundItemException("존재하지 않는 상품입니다.")));

        Delivery delivery = new Delivery();
        delivery.setAddress(member.get().getAddress());
        delivery.setStatus(DeliveryStatus.READY);

        OrderItem orderItem = OrderItem.createOrderItem(item, item.get().getPrice(), count);

        Order order = Order.createOrder(member.get(), delivery, orderItem);

        orderRepository.save(order);

        return order.getId();
    }

    @Override
    public void cancelOrder(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        order.get().cancel();
    }
}
