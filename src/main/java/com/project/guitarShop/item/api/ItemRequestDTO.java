package com.project.guitarShop.item.api;

import com.project.guitarShop.item.domain.Brand;
import com.project.guitarShop.item.domain.Category;
import lombok.Builder;

public record ItemRequestDTO(String name, int price, int quantity, Category category, Brand brand) {

    @Builder
    public ItemRequestDTO {

    }
}
