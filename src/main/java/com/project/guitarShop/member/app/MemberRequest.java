package com.project.guitarShop.member.app;

import com.project.guitarShop.address.domain.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberRequest {

    private String loginId;
    private String password;
    private String confirmPassword;
    private String name;
    private int age;
    private String phone;
    private String email;
    private String role;
    private Address address;

//    public static MemberRequest toRequest(MemberRequestDto memberRequestDTO) {
//        return MemberRequest.builder()
//                .loginId(memberRequestDTO.loginId())
//                .password(memberRequestDTO.password())
//                .confirmPassword(memberRequestDTO.confirmPassword())
//                .name(memberRequestDTO.name())
//                .age(memberRequestDTO.age())
//                .phone(memberRequestDTO.phone())
//                .email(memberRequestDTO.email())
//                .role(memberRequestDTO.role())
//                .address(memberRequestDTO.address())
//                .orders(memberRequestDTO.orders())
//                .build();
//    }
}
