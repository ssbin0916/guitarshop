package com.project.guitarshop.item.entity;

public class Quantity {

    private int value;

    public Quantity(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("수량은 음수일 수 없습니다.");
        }
        this.value = value;
    }
}
