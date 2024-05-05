package com.project.guitarShop.domain.address;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Address {

    private String address;
    private String addressDetail;
    private String request;

    public Address(String address, String addressDetail, String request) {
        this.address = address;
        this.addressDetail = addressDetail;
        this.request = request;
    }
}
