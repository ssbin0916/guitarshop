package com.project.guitarshop.dto.item;

import com.project.guitarshop.entity.item.Brand;
import com.project.guitarshop.entity.item.Category;

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
