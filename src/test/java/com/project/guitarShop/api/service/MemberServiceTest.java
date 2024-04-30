//package com.project.guitarShop.api.service;
//
//import com.project.guitarShop.domain.address.Address;
//import com.project.guitarShop.member.domain.Member;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@Transactional
//class MemberServiceTest {
//
//    @Autowired
//    MemberService memberService;
//
//    @Test
//    void join() {
//        Member member = Member.builder()
//                .loginId("loginId")
//                .password("password")
//                .confirmPassword("confirmPassword")
//                .name("name")
//                .birthDate("birthDate")
//                .phone("phone")
//                .email("email")
//                .address(new Address("address", "addressDetail", "request"))
//                .orders(null)
//                .build();
//        Long save = memberService.join(member);
//
//        assertThat(save).isEqualTo(1L);
//    }
//
//    @Test
//    void update() {
//        Member member = Member.builder()
//                .loginId("loginId")
//                .password("password")
//                .confirmPassword("confirmPassword")
//                .name("name")
//                .birthDate("birthDate")
//                .phone("phone")
//                .email("email")
//                .address(new Address("address", "addressDetail", "request"))
//                .orders(null)
//                .build();
//        Long save = memberService.join(member);
//
//        memberService.update(save, "update");
//
//        assertThat(member.getLoginId()).isEqualTo("update");
//    }
//
//}