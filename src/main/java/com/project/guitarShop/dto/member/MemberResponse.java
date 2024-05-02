package com.project.guitarShop.dto.member;

import com.project.guitarShop.domain.address.Address;
import com.project.guitarShop.domain.member.Member;
import lombok.Getter;

public class MemberResponse {

    @Getter
    public static class LoginResponse {
        private String loginId;
        private String name;
        private String role;

        public LoginResponse(Member member) {
            this.loginId = member.getLoginId();
            this.name = member.getName();
            this.role = member.getRole();
        }
    }

    @Getter
    public static class JoinResponse {
        private Long id;
        private String loginId;
        private String name;
        private int age;
        private String email;
        private Address address;

        public JoinResponse(Member member) {
            this.id = member.getId();
            this.loginId = member.getLoginId();
            this.name = member.getName();
            this.age = member.getAge();
            this.email = member.getEmail();
            this.address = member.getAddress();
        }
    }

    @Getter
    public static class UpdateInfoResponse {
        private String phone;
        private String email;
        private Address address;

        public UpdateInfoResponse(Member member) {
            this.phone = member.getPhone();
            this.email = member.getEmail();
            this.address = member.getAddress();
        }
    }

    @Getter
    public static class UpdatePasswordResponse {
        private Long id;

        public UpdatePasswordResponse(Member member) {
            this.id = member.getId();
        }
    }

}
