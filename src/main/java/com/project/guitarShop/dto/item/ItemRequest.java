package com.project.guitarShop.dto.item;

import com.project.guitarShop.entity.item.Brand;
import com.project.guitarShop.entity.item.Category;

public class ItemRequest {

    public record AddItemRequest(
            String name,
            Integer price,
            Integer quantity,
            Category category,
            Brand brand
    ) {
    }

    public record FindItemRequest(
            String name,
            Category category,
            Brand brand,
            Boolean sort
    ) {
    }
}
