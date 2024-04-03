package com.project.guitarShop.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class Cart {

    private Long id;

    @NotBlank
    private Long memberId;

    @NotBlank
    private Long productId;

    @NotBlank
    @Min(value = 1)
    private Integer quantity;
}
