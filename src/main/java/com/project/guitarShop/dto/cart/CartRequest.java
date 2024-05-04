package com.project.guitarShop.dto.cart;

import com.project.guitarShop.domain.cart.Cart;
import com.project.guitarShop.domain.member.Member;
import com.project.guitarShop.domain.orderItem.OrderItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class CartRequest {

    @Getter
    @Setter
    public static class AddCartRequestDto {
        private Member member;
        private List<OrderItem> orderItems;

        public Cart toDomain() {
            return Cart.builder()
                    .member(member)
                    .orderItems(orderItems)
                    .build();
        }
    }

    @Getter
    @Setter
    public static class CartListRequestDto {

    }
}
