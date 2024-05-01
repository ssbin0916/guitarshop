package com.project.guitarShop.member.app;

import com.project.guitarShop.address.domain.Address;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponse {

    private Long id;
    private String loginId;
    private String name;
    private int age;
    private String phone;
    private String email;
    private String role;
    private Address address;

    @Builder
    public MemberResponse(Long id, String loginId, String name, int age, String phone, String email, String role, Address address) {
        this.id = id;
        this.loginId = loginId;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.address = address;
    }
}
