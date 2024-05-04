package com.project.guitarShop.dto.member;

import com.project.guitarShop.domain.address.Address;
import com.project.guitarShop.domain.member.Member;
import lombok.Getter;

public class MemberResponse {

    @Getter
    public static class LoginResponse {
        private final String loginId;
        private final String name;
        private final String gender;
        private final Address address;
        private final String role;

        public LoginResponse(Member member) {
            this.loginId = member.getLoginId();
            this.name = member.getName();
            this.gender = member.getGender();
            this.address = member.getAddress();
            this.role = member.getRole();
        }
    }

    @Getter
    public static class JoinResponse {
        private final Long id;
        private final String loginId;
        private final String name;
        private final String gender;
        private final String email;
        private final Address address;

        public JoinResponse(Member member) {
            this.id = member.getId();
            this.loginId = member.getLoginId();
            this.name = member.getName();
            this.gender = member.getGender();
            this.email = member.getEmail();
            this.address = member.getAddress();
        }
    }
}
