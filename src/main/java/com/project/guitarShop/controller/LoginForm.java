package com.project.guitarShop.controller;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class LoginForm {

    @NotEmpty(message = "아이디를 입력하세요")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "아이디는 영문 또는 숫자로만 가능합니다.")
    private String loginId;

    @NotEmpty(message = "비밀번호를 입력하세요")
    private String password;
}
