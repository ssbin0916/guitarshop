package com.project.guitarShop.member.api;

import com.project.guitarShop.address.domain.Address;
import com.project.guitarShop.order.domain.Order;
import lombok.Builder;

import java.util.List;

public record MemberRequestDTO(String loginId, String password, String confirmPassword, String name, int age, String phone,
                               String email, String role, Address address, List<Order> orders) {

    @Builder
    public MemberRequestDTO {

    }

}
