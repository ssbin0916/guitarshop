package com.project.guitarShop.dto.member;

import com.project.guitarShop.entity.address.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class MemberRequest {

    @Getter
    @Builder
    public static class LoginRequest {

        @Email(message = "이메일 입력은 필수입니다.")
        private String loginEmail;
        @NotEmpty(message = "비밀번호 입력은 필수입니다.")
        private String password;

        public LoginRequest() {

        }

        public LoginRequest(String loginEmail, String password) {
            this.loginEmail = loginEmail;
            this.password = password;
        }
    }

    @Getter
    @Setter
    @Builder
    public static class JoinRequest {

        @Email
        @NotEmpty(message = "이메일 입력은 필수입니다.")
        private String loginEmail;

        @NotEmpty(message = "비밀번호 입력은 필수입니다.")
//        @Size(min = 8, max = 20, message = "비밀번호는 8 ~ 20 자리 사이로 입력해주세요.")
//        @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "비밀번호는 대문자, 숫자, 특수문자를 하나씩 포함해야 합니다.")
        private String password;

        @NotEmpty(message = "비밀번호 확인 입력은 필수입니다.")
        private String confirmPassword;

        @NotEmpty(message = "이름 입력은 필수입니다.")
        private String name;

        @NotEmpty(message = "전화번호 입력은 필수입니다.")
        private String phone;

        private Address address;

    }


    @Getter
    @Builder
    public static class UpdateInfoRequest {
        private String phone;
        private Address address;
    }

    @Getter
    @Builder
    public static class UpdatePasswordRequest {
        private String currentPassword;
        private String newPassword;
        private String confirmPassword;
    }
}
