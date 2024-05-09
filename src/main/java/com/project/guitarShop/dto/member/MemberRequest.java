package com.project.guitarShop.dto.member;

import com.project.guitarShop.domain.address.Address;
import com.project.guitarShop.domain.member.Member;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MemberRequest {

    @Getter
    @Setter
    public static class LoginRequest {

        @Email
        private String loginEmail;
        @NotEmpty(message = "비밀번호 입력은 필수입니다.")
        private String password;
    }

    @Getter
    @Setter
    @Builder
    public static class JoinRequest {

        @Email
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

        @Valid
        private Address address;

    }


    @Getter
    @Setter
    @Builder
    public static class UpdateInfoRequest {
        private String phone;
        private Address address;
    }

    @Getter
    @Setter
    @Builder
    public static class UpdatePasswordRequest {
        private String password;
        private String confirmPassword;
    }
}
