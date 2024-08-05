package com.project.guitarshop.event.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Event event;
    private String code;

    public Coupon(Event event) {
        this.event = event;
        this.code = UUID.randomUUID().toString();
    }
}
