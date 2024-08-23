package com.project.guitarshop.item.entity;

public class Price {

    private int value;

    public Price(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("가격은 음수일 수 없습니다.");
        }
        this.value = value;
    }
}
