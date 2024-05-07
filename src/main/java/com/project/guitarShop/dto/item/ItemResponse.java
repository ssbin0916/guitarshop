package com.project.guitarShop.dto.item;

import com.project.guitarShop.domain.item.Brand;
import com.project.guitarShop.domain.item.Category;
import com.project.guitarShop.domain.item.Item;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

public class ItemResponse {

    @Getter
    public static class AddItemResponse {
        private Long id;
        private String name;
        private int price;
        private int quantity;
        private Category category;
        private Brand brand;

        @QueryProjection
        public AddItemResponse(Item item) {
            this.id = item.getId();
            this.name = item.getName();
            this.price = item.getPrice();
            this.quantity = item.getQuantity();
            this.category = item.getCategory();
            this.brand = item.getBrand();
        }
    }

    @Getter
    public static class FindItemResponse {
        private String name;
        private Integer price;
        private Category category;
        private Brand brand;

        @QueryProjection
        public FindItemResponse(String name, int price, Category category, Brand brand) {
            this.name = name;
            this.price = price;
            this.category = category;
            this.brand = brand;
        }
    }
}
