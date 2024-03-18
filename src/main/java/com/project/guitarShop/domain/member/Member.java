package com.project.guitarShop.domain.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
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
    private String phone;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String birth_date;
    @NotEmpty
    private String address;
    @NotEmpty
    private String gender;

    @NotEmpty
    private Role role;
}
