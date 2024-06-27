package com.project.guitarShop.dto.member;

import com.project.guitarShop.entity.address.Address;

public class MemberRequest {

    public record LoginRequest(
            String loginEmail,
            String password
    ) {
    }

    public record JoinRequest(
            String loginEmail,
            String password,
            String confirmPassword,
            String name,
            String phone,
            Address address
    ) {
    }

    public record UpdateInfoRequest(
            String phone,
            Address address
    ) {
    }

    public record UpdatePasswordRequest(
            String currentPassword,
            String newPassword,
            String confirmPassword
    ) {
    }
}
