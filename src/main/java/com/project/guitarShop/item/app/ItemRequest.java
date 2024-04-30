package com.project.guitarShop.item.app;

import com.project.guitarShop.item.api.ItemRequestDTO;
import com.project.guitarShop.item.domain.Brand;
import com.project.guitarShop.item.domain.Category;
import lombok.Builder;


public record ItemRequest(String name, int price, int quantity, Category category, Brand brand) {

    @Builder
    public ItemRequest {

    }

    public static ItemRequest toRequest(ItemRequestDTO itemRequestDTO) {
        return ItemRequest.builder()
                .name(itemRequestDTO.name())
                .price(itemRequestDTO.price())
                .quantity(itemRequestDTO.quantity())
                .category(itemRequestDTO.category())
                .brand(itemRequestDTO.brand())
                .build();
    }
}
