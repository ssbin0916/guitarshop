package com.project.guitarShop.dto.item;

import com.project.guitarShop.domain.item.Brand;
import com.project.guitarShop.domain.item.Category;
import com.project.guitarShop.domain.item.Item;
import lombok.*;


public class ItemRequest {

    @Getter
    @Setter
    @Builder
    public static class AddItemRequest {
        private String name;
        private int price;
        private int quantity;
        private Category category;
        private Brand brand;

        public Item toDomain() {
            return Item.builder()
                    .name(name)
                    .price(price)
                    .quantity(quantity)
                    .category(category)
                    .brand(brand)
                    .build();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class FindItemRequest {
        private String name;
        private Integer price;
        private Category category;
        private Brand brand;
        private boolean sortAscending;
    }
}
