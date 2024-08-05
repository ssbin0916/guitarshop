package com.project.guitarshop.member.service;


import com.project.guitarshop.member.dto.CustomUserDetails;
import com.project.guitarshop.member.entity.Member;
import com.project.guitarshop.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String loginEmail) throws UsernameNotFoundException {

        Member userData = memberRepository.findByLoginEmail(loginEmail)
                .orElseThrow();

        return new CustomUserDetails(userData);
    }
}
