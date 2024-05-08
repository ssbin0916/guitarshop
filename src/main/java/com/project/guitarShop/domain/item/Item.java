package com.project.guitarShop.domain.item;

import com.project.guitarShop.exception.NotEnoughStockException;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;
    private int quantity;

    @Enumerated(EnumType.STRING)
    private Category category;
    @Enumerated(EnumType.STRING)
    private Brand brand;

    @Builder
    public Item(String name, int price, int quantity, Category category, Brand brand) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.brand = brand;
    }

    public void addStock(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("잘못된 수량입니다.");
        }
        this.quantity += quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.quantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("삭제할 재고가 남아 있지 않습니다.");
        }
        this.quantity = restStock;
    }
}
