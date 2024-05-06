package com.project.guitarShop;

import com.project.guitarShop.domain.address.Address;
import com.project.guitarShop.dto.member.MemberRequest.JoinRequest;
import com.project.guitarShop.dto.member.MemberResponse.JoinResponse;
import com.project.guitarShop.service.member.MemberService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final MemberService memberService;

        public void dbInit() {
        }

        JoinRequest request = JoinRequest.builder()
                .loginId("loginId1")
                .password("password")
                .confirmPassword("password")
                .name("name")
                .rrn("000000-1111111")
                .phone("phone")
                .email("email")
                .address(new Address("add", "re", "ss"))
                .build();

        JoinResponse response = memberService.join(request);
    }
}
