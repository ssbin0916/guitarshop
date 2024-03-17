package com.project.guitarShop.domain.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Member {

    private Long id;

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;
    @NotEmpty
    private Integer age;
    @NotEmpty
    private String gender;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String phone;
    @NotEmpty
    private String address;

    @NotEmpty
    private Role role;
}
