package com.project.guitarShop.domain.cart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Cart {

    private Long id;

    @NotNull
    private Long memberId;
    @NotNull
    private Long productId;
    @NotNull
    @Min(value = 1)
    private Integer quantity;
}
