package com.project.guitarshop.member.dto;

import com.project.guitarshop.member.entity.Address;
import com.project.guitarshop.member.entity.Member;

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
}


