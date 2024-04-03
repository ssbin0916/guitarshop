package com.project.guitarShop.domain;

import com.project.guitarShop.domain.product.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class OrderItem {

    private Long id;

    @NotEmpty
    private Product product;

    @NotEmpty
    private Order order;

    @NotEmpty
    private Integer price;

    @NotEmpty
    @Min(value = 1)
    private Integer quantity;

    public static OrderItem createOrderItem(Product product, int price, int quantity) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setPrice(price);
        orderItem.setQuantity(quantity);

        product.removeStock(quantity);
        return orderItem;
    }

    public void cancel() {
        getProduct().addStock(quantity);
    }

    public int getTotalPrice() {
        return getPrice() * getQuantity();
    }
}
