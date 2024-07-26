package com.project.guitarshop.entity.item;

import com.project.guitarshop.entity.BaseEntity;
import com.project.guitarshop.exception.item.NotFoundItemException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer price;
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Brand brand;

    @Builder
    public Item(String name, Integer price, Integer quantity, Category category, Brand brand) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.brand = brand;
    }

    public void addStock(Integer quantity) {

        if (quantity < 0) {
            throw new NotFoundItemException("해당 상품을 찾을 수 없습니다.");
        }
        this.quantity += quantity;
    }

    public void removeStock(Integer quantity) {

        int restStock = this.quantity - quantity;

        if (restStock < 0) {
            throw new NotFoundItemException("해당 상품을 찾을 수 없습니다.");
        }
        this.quantity = restStock;
    }

}
