package com.project.guitarShop.member.app;

import com.project.guitarShop.address.domain.Address;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponse {

    private final String loginId;
    private final String password;
    private final String confirmPassword;
    private final String name;
    private final int age;
    private final String phone;
    private final String email;
    private final String role;
    private final Address address;

    @Builder
    public MemberResponse(String loginId, String password, String confirmPassword, String name,
                          int age, String phone, String email, String role, Address address) {
        this.loginId = loginId;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.address = address;
    }
}
