package com.project.guitarShop.dto.member;

import com.project.guitarShop.domain.address.Address;
import com.project.guitarShop.domain.member.Member;
import lombok.Getter;

public class MemberResponse {

    @Getter
    public static class LoginResponse {
        private final String loginEmail;
        private final String name;
        private final Address address;
        private final String role;

        public LoginResponse(Member member) {
            this.loginEmail = member.getLoginEmail();
            this.name = member.getName();
            this.address = member.getAddress();
            this.role = member.getRole();
        }
    }

    @Getter
    public static class JoinResponse {
        private final Long id;
        private final String loginEmail;
        private final String name;
        private final Address address;

        public JoinResponse(Member member) {
            this.id = member.getId();
            this.loginEmail = member.getLoginEmail();
            this.name = member.getName();
            this.address = member.getAddress();
        }
    }

    @Getter
    public static class UpdateInfoResponse {
        private final Long id;
        private final String loginEmail;
        private final String name;
        private final Address address;

        public UpdateInfoResponse(Member member) {
            this.id = member.getId();
            this.loginEmail = member.getLoginEmail();
            this.name = member.getName();
            this.address = member.getAddress();
        }
    }

    @Getter
    public static class UpdatePasswordResponse {
        private final Long id;
        private final String loginEmail;
        private final String name;
        private final Address address;

        public UpdatePasswordResponse(Member member) {
            this.id = member.getId();
            this.loginEmail = member.getLoginEmail();
            this.name = member.getName();
            this.address = member.getAddress();
        }
    }
}
