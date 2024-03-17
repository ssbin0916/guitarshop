package com.project.guitarShop.web.interceptor;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginForm {

    @NotEmpty
    private String memberId;

    @NotEmpty
    private String password;
}
