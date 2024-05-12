package com.project.guitarShop.dto.order;

import com.project.guitarShop.domain.delivery.Delivery;
import com.project.guitarShop.domain.delivery.DeliveryStatus;
import com.project.guitarShop.domain.order.Order;
import com.project.guitarShop.domain.order.OrderStatus;
import com.project.guitarShop.domain.orderItem.OrderItem;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponse {

    @Getter
    public static class CreateOrdersResponse {
        private final Long id;
        private final List<OrderItem> orderItems;
        private final LocalDateTime orderDate;
        private final OrderStatus orderStatus;
        private final Delivery delivery;
        private final DeliveryStatus deliveryStatus;
        private final int totalPrice;

        public CreateOrdersResponse(Order order) {
            this.id = order.getId();
            this.orderItems = order.getOrderItems();
            this.orderDate = order.getOrderDate();
            this.orderStatus = order.getOrderStatus();
            this.delivery = order.getDelivery();
            this.deliveryStatus = order.getDelivery().getStatus();
            this.totalPrice = order.getTotalPrice();
        }
    }

    @Getter
    public static class OrderFormCartResponse {
        private final Long id;
        private final List<OrderItem> orderItems;
        private final LocalDateTime orderDate;
        private final OrderStatus orderStatus;
        private final Delivery delivery;
        private final DeliveryStatus deliveryStatus;
        private final int totalPrice;

        public OrderFormCartResponse(Order order) {
            this.id = order.getId();
            this.orderItems = order.getOrderItems();
            this.orderDate = order.getOrderDate();
            this.orderStatus = order.getOrderStatus();
            this.delivery = order.getDelivery();
            this.deliveryStatus = order.getDelivery().getStatus();
            this.totalPrice = order.getTotalPrice();
        }
    }

}
