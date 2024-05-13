package com.project.guitarShop.dto.order;

import com.project.guitarShop.entity.delivery.Delivery;
import com.project.guitarShop.entity.delivery.DeliveryStatus;
import com.project.guitarShop.entity.order.Order;
import com.project.guitarShop.entity.order.OrderStatus;
import com.project.guitarShop.entity.orderItem.OrderItem;
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
        private final String itemName;
        private final Integer price;

        public CreateOrdersResponse(Order order) {
            this.id = order.getId();
            this.orderItems = order.getOrderItems();
            this.orderDate = order.getOrderDate();
            this.orderStatus = order.getOrderStatus();
            this.delivery = order.getDelivery();
            this.deliveryStatus = order.getDelivery().getStatus();
            this.itemName = order.getOrderItems().get(0).getName();
            this.price = order.getPrice();
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
        private final String itemName;

        public OrderFormCartResponse(Order order) {
            this.id = order.getId();
            this.orderItems = order.getOrderItems();
            this.orderDate = order.getOrderDate();
            this.orderStatus = order.getOrderStatus();
            this.delivery = order.getDelivery();
            this.deliveryStatus = order.getDelivery().getStatus();
            this.itemName = order.getOrderItems().get(0).getName();
        }
    }
}
