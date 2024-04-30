//package com.project.guitarShop.controller;
//
//import com.project.guitarShop.member.app.LoginService;
//import com.project.guitarShop.dto.member.LoginForm;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//@RequiredArgsConstructor
//public class LoginControllerMyBatis {
//
//    private final LoginService loginService;
//
//    @GetMapping("/login")
//    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
//        return "login/loginForm";
//    }
//
//    @PostMapping("/login")
//    private String login(@Valid @ModelAttribute("loginForm") LoginForm form, BindingResult bindingResult) {
//        return null;
//    }
//}
