package com.project.guitarShop.member.app;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberUpdatePasswordDTO {

    private final String password;
    private final String confirmPassword;

    @Builder
    public MemberUpdatePasswordDTO(String password, String confirmPassword) {
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
