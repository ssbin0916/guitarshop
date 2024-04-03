package com.project.guitarShop.controller;

import com.project.guitarShop.domain.member.Member;
import com.project.guitarShop.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String joinForm(@ModelAttribute("member") Member member) {
        return "joinForm";
    }

    @PostMapping("/join")
    public String join(@Valid @ModelAttribute("member") Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "joinForm";
        }

        Optional<Member> existLoginId = memberService.findByLoginId(member.getLoginId());
        if (existLoginId.isPresent()) {
            bindingResult.rejectValue("loginId", "duplicate", "이미 사용 중인 아이디입니다.");
            return "joinForm";
        }

        if (!member.getPassword().equals(member.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "password.mismatch", "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            return "joinForm";
        }

        memberService.save(member);
        return "redirect:/";
    }
}
