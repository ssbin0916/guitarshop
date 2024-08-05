package com.project.guitarshop.item.dto;

import com.project.guitarshop.item.entity.Brand;
import com.project.guitarshop.item.entity.Category;

import java.util.List;

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
            List<String> autoCompleteNames,
            Category category,
            Brand brand,
            Boolean sort
    ) {
    }
}
