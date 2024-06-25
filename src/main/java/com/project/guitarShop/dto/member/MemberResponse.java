package com.project.guitarShop.dto.member;

import com.project.guitarShop.entity.address.Address;
import com.project.guitarShop.entity.member.Member;
import lombok.Getter;

public class MemberResponse {

    public record LoginResponse(
            String loginEmail,
            String name,
            Address address,
            String role
    ) {
        public LoginResponse(Member member) {
            this(
                    member.getLoginEmail(),
                    member.getName(),
                    member.getAddress(),
                    member.getRole()
            );
        }
    }

    public record JoinResponse(
            boolean success,
            String message
    ) {
    }

    public record UpdateInfoResponse(
            String loginEmail,
            String name,
            Address address
    ) {
        public UpdateInfoResponse(Member member) {
            this(
                    member.getLoginEmail(),
                    member.getName(),
                    member.getAddress()
            );
        }
    }

    public record UpdatePasswordResponse(
            String loginEmail,
            String name
    ) {
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
