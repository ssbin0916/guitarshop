package com.project.guitarShop.entity.address;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Address {

    @NotEmpty(message = "주소를 입력해야 합니다.")
    private String address;

    @NotEmpty(message = "상세 주소를 입력해야 합니다.")
    private String addressDetail;

    private String request;

    public Address(String address, String addressDetail, String request) {
        this.address = address;
        this.addressDetail = addressDetail;
        this.request = request;
    }
}
