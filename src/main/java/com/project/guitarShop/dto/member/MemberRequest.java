package com.project.guitarShop.dto.member;

import com.project.guitarShop.domain.address.Address;
import com.project.guitarShop.domain.member.Member;
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

        @NotEmpty(message = "아이디 입력은 필수입니다.")
        private String loginId;
        @NotEmpty(message = "비밀번호 입력은 필수입니다.")
        private String password;
    }

    @Getter
    @Setter
    @Builder
    public static class JoinRequest {

        @NotEmpty(message = "아이디 입력은 필수입니다.")
        @Pattern(regexp = "^[a-zA-Z0-9]{4,20}$", message = "아이디는 영문 또는 숫자만 가능합니다.(4 ~ 20자)")
        private String loginId;

        @NotEmpty(message = "비밀번호 입력은 필수입니다.")
        @Size(min = 8, max = 20, message = "비밀번호는 8 ~ 20 자리 사이로 입력해주세요.")
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "비밀번호는 대문자, 숫자, 특수문자를 하나씩 포함해야 합니다.")
        private String password;

        @NotEmpty(message = "비밀번호 확인 입력은 필수입니다.")
        private String confirmPassword;

        @NotEmpty(message = "이름 입력은 필수입니다.")
        private String name;

        @NotEmpty(message = "주민 등록 번호 입력은 필수입니다.")
        private String rrn;

        @NotEmpty(message = "성별 입력은 필수입니다.")
        private String gender;

        @NotEmpty(message = "전화번호 입력은 필수입니다.")
        private String phone;

        @NotEmpty(message = "이메일 입력은 필수입니다.")
        private String email;

        @NotEmpty(message = "주소 입력은 필수입니다.")
        private Address address;

        public Member toEntity() {
            return Member.builder()
                    .loginId(loginId)
                    .password(password)
                    .name(name)
                    .rrn(rrn)
                    .phone(phone)
                    .email(email)
                    .address(address)
                    .build();
        }
    }


    @Getter
    @Setter
    @Builder
    public static class UpdateInfoRequest {
        private String phone;
        private String email;
        private Address address;

        public Member toDomain() {
            return Member.builder()
                    .phone(phone)
                    .email(email)
                    .address(address)
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    public static class UpdatePasswordRequest {
        private String password;
        private String confirmPassword;

        public Member toDomain(BCryptPasswordEncoder passwordEncoder) {
            return Member.builder()
                    .password(passwordEncoder.encode(password))
                    .build();
        }
    }
}
