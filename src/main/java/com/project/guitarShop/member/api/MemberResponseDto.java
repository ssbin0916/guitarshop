package com.project.guitarShop.member.api;

import com.project.guitarShop.address.domain.Address;
import lombok.Builder;

public record MemberResponseDto(String name, int age, String phone, String email, Address address) {

    @Builder
    public MemberResponseDto {

    }
}

