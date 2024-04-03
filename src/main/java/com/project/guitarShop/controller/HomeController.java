package com.project.guitarShop.controller;

import com.project.guitarShop.SessionConst;
import com.project.guitarShop.domain.member.Member;
import com.project.guitarShop.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberMapper memberMapper;

    @GetMapping("/")
    public String homeLogin(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model) {

        if (loginMember == null) {
            return "index";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";
    }
}
