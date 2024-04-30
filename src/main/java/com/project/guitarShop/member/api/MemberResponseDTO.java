package com.project.guitarShop.member.api;

import com.project.guitarShop.domain.address.Address;
import lombok.Builder;

public record MemberResponseDTO(String name, int age, String phone, String email, Address address) {

    @Builder
    public MemberResponseDTO{

    }
}

