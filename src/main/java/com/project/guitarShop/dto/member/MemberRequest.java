package com.project.guitarShop.dto.member;

import com.project.guitarShop.domain.address.Address;
import com.project.guitarShop.domain.member.Member;
import com.project.guitarShop.domain.order.Order;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class MemberRequest {

    @Getter
    @Setter
    public static class LoginRequestDto {
        private String loginId;
        private String password;
    }

    @Getter
    @Setter
    public static class JoinRequestDto {

        @Pattern(regexp = "^[a-zA-Z0-9]{4,20}$", message = "아이디는 영문 또는 숫자만 가능합니다.(4 ~ 20자)")
        @NotEmpty
        private String loginId;

        @NotEmpty
        @Size(min = 4, max = 20)
        private String password;
        private String confirmPassword;
        private String name;
        private int age;
        private String phone;
        private String email;
        private Address address;

        public Member toEntity(BCryptPasswordEncoder passwordEncoder) {
            return Member.builder()
                    .loginId(loginId)
                    .password(passwordEncoder.encode(password))
                    .confirmPassword(passwordEncoder.encode(confirmPassword))
                    .name(name)
                    .age(age)
                    .phone(phone)
                    .email(email)
                    .address(address)`
                    .build();
        }
    }


    @Builder.Default
    private List<Order> orders = new ArrayList<>();

}
