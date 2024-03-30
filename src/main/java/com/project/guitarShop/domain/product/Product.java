package com.project.guitarShop.domain.product;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Product {

    private Long id;

    @NotEmpty
    private String name;

    @NotNull
    private Integer price;

    private String image;

    @NotNull
    private String count;

    @NotNull
    private Category category;

}
