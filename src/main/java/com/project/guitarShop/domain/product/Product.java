package com.project.guitarShop.domain.product;

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
    private String count;

    @NotEmpty
    private Category category;

    @NotEmpty
    private Brand brand;
}
