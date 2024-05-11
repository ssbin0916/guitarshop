package com.project.guitarShop.dto.item;

import com.project.guitarShop.domain.item.Brand;
import com.project.guitarShop.domain.item.Category;
import lombok.Builder;
import lombok.Getter;


public class ItemRequest {

    @Getter
    @Builder
    public static class AddItemRequest {
        private String name;
        private Integer price;
        private Integer quantity;
        private Category category;
        private Brand brand;
    }

    @Getter
    @Builder
    public static class FindItemRequest {
        private String name;
        private Integer price;
        private Category category;
        private Brand brand;
        private boolean sortAscending;
    }
}
