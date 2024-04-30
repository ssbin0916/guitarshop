package com.project.guitarShop.member.app;

import com.project.guitarShop.address.domain.Address;
import com.project.guitarShop.order.domain.Order;
import com.project.guitarShop.member.api.MemberRequestDTO;
import lombok.Builder;

import java.util.List;

public record MemberRequest(String loginId, String password, String confirmPassword, String name, int age, String phone,
                            String email, String role, Address address, List<Order> orders) {

    @Builder
    public MemberRequest {

    }

    public static MemberRequest toRequest(MemberRequestDTO memberRequestDTO) {
        return MemberRequest.builder()
                .loginId(memberRequestDTO.loginId())
                .password(memberRequestDTO.password())
                .confirmPassword(memberRequestDTO.confirmPassword())
                .name(memberRequestDTO.name())
                .age(memberRequestDTO.age())
                .phone(memberRequestDTO.phone())
                .email(memberRequestDTO.email())
                .role(memberRequestDTO.role())
                .address(memberRequestDTO.address())
                .orders(memberRequestDTO.orders())
                .build();
    }
}
