package com.project.guitarShop.dto.member;

import com.project.guitarShop.domain.address.Address;
import com.project.guitarShop.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class MemberResponse {

    @Getter
    @Setter
    public static class LoginResponseDto {
        private Long id;
        private String loginId;

        public LoginResponseDto(Member member) {
            this.id = member.getId();
            this.loginId = member.getLoginId();
        }
    }


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
