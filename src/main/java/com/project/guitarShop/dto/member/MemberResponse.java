package com.project.guitarShop.dto.member;

import com.project.guitarShop.entity.address.Address;
import com.project.guitarShop.entity.member.Member;

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
            String loginEmail,
            String name,
            Address address
    ) {
        public JoinResponse(Member member) {
            this(
                    member.getLoginEmail(),
                    member.getName(),
                    member.getAddress()
            );
        }
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
            boolean success,
            String message
    ) {
    }
}


