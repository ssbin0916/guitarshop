package com.project.guitarShop.domain.member;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Member {

    private Long id;

    @NotEmpty
    private String memberId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;
    @NotEmpty
    private Integer age;
    @NotEmpty
    private String gender;
    @NotEmpty
    private String email;
    @NotEmpty
    private String phone;
    @NotEmpty
    private String address;

    @NotEmpty
    private Role role;
}
