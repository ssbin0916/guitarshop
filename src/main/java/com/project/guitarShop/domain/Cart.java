package com.project.guitarShop.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class Cart {

    private Long id;

    @NotEmpty
    private Long memberId;

    @NotEmpty
    private Long productId;

    @NotEmpty
    @Min(value = 1)
    private Integer quantity;
}
