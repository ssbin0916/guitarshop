package com.project.guitarShop.order.app;

import com.project.guitarShop.delivery.domain.Delivery;
import com.project.guitarShop.delivery.domain.DeliveryStatus;
import com.project.guitarShop.exception.NotFoundItemException;
import com.project.guitarShop.exception.NotFoundMemberException;
import com.project.guitarShop.item.domain.Item;
import com.project.guitarShop.item.repository.ItemRepository;
import com.project.guitarShop.member.domain.Member;
import com.project.guitarShop.member.repository.MemberRepository;
import com.project.guitarShop.order.domain.Order;
import com.project.guitarShop.order.repository.OrderRepository;
import com.project.guitarShop.orderItem.domain.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

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
