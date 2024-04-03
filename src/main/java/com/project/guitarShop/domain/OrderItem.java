package com.project.guitarShop.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class OrderItem {

    private Long id;

    @NotNull
    private Long orderId;
    @NotNull
    private Long productId;
    @NotNull
    @Min(value = 1)
    private Integer quantity;
}
