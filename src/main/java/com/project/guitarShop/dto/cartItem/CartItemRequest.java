package com.project.guitarShop.dto.cartItem;

import com.project.guitarShop.entity.item.Item;
import com.project.guitarShop.entity.member.Member;
import lombok.Builder;
import lombok.Getter;

public class CartItemRequest {

    @Getter
    @Builder
    public static class AddCartItemRequest {
        private final Member member;
        private final Item item;
        private final Integer quantity;
    }

}
