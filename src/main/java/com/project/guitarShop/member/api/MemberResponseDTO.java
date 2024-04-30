package com.project.guitarShop.member.api;

import com.project.guitarShop.address.domain.Address;
import lombok.Builder;

public record MemberResponseDTO(String name, int age, String phone, String email, Address address) {

    @Builder
    public MemberResponseDTO{

    }
}

