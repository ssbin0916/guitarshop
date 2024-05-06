package com.project.guitarShop.dto.order;

import com.project.guitarShop.domain.delivery.Delivery;
import com.project.guitarShop.domain.item.Item;
import com.project.guitarShop.domain.member.Member;
import com.project.guitarShop.domain.order.Order;
import com.project.guitarShop.domain.order.OrderStatus;
import com.project.guitarShop.domain.orderItem.OrderItem;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class OrderRequest {

    @Getter
    @Setter
    public static class CreateOrderRequest {
        private Member member;
        private List<Item> items;
        private List<Integer> counts;

        public Order toEntity(Member member, List<OrderItem> orderItems, Delivery delivery) {
            return Order.createOrder(member, delivery, orderItems, LocalDateTime.now(), OrderStatus.ORDER);
        }

        public List<OrderItem> createOrderItems(List<Item> items) {
            List<OrderItem> orderItems = new ArrayList<>();
            IntStream.range(0, items.size()).forEach(index -> {
                Item item = items.get(index);
                int count = this.counts.get(index);
                OrderItem orderItem = new OrderItem(item, item.getPrice(), count);
                orderItems.add(orderItem);
            });
            return orderItems;
        }
    }
}
