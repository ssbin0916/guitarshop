package com.project.guitarShop.domain.product;

import com.project.guitarShop.exception.NotEnoughStockException;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Product {

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private Integer price;

    @NotEmpty
    private String image;

    @NotEmpty
    private String description;

    @NotEmpty
    private Integer quantity;

    @NotEmpty
    private Category category;

    @NotEmpty
    private Brand brand;

    public void addStock(int quantity) {
        this.quantity += quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.quantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.quantity = restStock;
    }
}
