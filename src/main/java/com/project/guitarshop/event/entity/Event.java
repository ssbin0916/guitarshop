package com.project.guitarshop.event.entity;

import lombok.Getter;

@Getter
public enum Event {

    CHICKEN("치킨");

    private final String name;

    Event(String name) {
        this.name = name;
    }
}
