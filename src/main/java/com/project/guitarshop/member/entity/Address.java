package com.project.guitarshop.member.entity;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    private String address;

    private String addressDetail;

    private String request;

    @Builder
    public Address(String address, String addressDetail, String request) {
        this.address = address;
        this.addressDetail = addressDetail;
        this.request = request;
    }

}
