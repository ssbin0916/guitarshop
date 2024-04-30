package com.project.guitarShop.member.app;

import com.project.guitarShop.domain.address.Address;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberUpdateDTO {

    private final String phone;
    private final String email;
    private final Address address;

    @Builder
    public MemberUpdateDTO(String phone, String email, Address address) {
        this.phone = phone;
        this.email = email;
        this.address = address;
    }
}
