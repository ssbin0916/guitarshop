package com.project.guitarShop.dto.cart;

import com.project.guitarShop.domain.member.Member;
import com.project.guitarShop.domain.orderItem.OrderItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class CartResponse {

    @Getter
    @Setter
    public static class AddCartRequestDto {

    }

    private Long id;
    private Member member;
    private List<OrderItem> orderItems;

}
