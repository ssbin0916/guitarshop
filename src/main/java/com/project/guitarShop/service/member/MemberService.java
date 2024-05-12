package com.project.guitarShop.service.member;

import com.project.guitarShop.domain.member.Member;
import com.project.guitarShop.dto.member.CustomUserDetails;
import com.project.guitarShop.dto.member.MemberRequest.*;
import com.project.guitarShop.dto.member.MemberResponse.*;
import com.project.guitarShop.exception.ExistMemberException;
import com.project.guitarShop.exception.NotFoundMemberException;
import com.project.guitarShop.exception.ValidatePasswordException;
import com.project.guitarShop.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public JoinResponse join(JoinRequest request) {
        validateExistLoginId(request);
        validateConfirmPassword(request.getPassword(), request.getConfirmPassword());

        Member member = Member.builder()
                .loginEmail(request.getLoginEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .phone(request.getPhone())
                .address(request.getAddress())
                .role("USER")
                .build();

        memberRepository.save(member);

        member.prePersist();

        return new JoinResponse(member);
    }

    public UpdateInfoResponse updateInfo(Long id, UpdateInfoRequest request) {

        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundMemberException("해당 회원을 찾을 수 없습니다."));

        boolean isUpdated = false;
        StringBuilder errorMessages = new StringBuilder();

        if (request.getPhone() != null && !request.getPhone().isEmpty()) {
            member.updatePhone(request.getPhone());
            isUpdated = true;
        }

        if (request.getAddress() != null) {
            member.updateAddress(request.getAddress());
            isUpdated = true;
        }

        if (isUpdated) {
            memberRepository.save(member);
        } else {
            errorMessages.append("변경할 정보가 유효하지 않습니다.");
            throw new IllegalArgumentException("회원 정보 업데이트에 실패하였습니다");
        }

        return new UpdateInfoResponse(member);
    }


    public UpdatePasswordResponse updatePassword(Long id, UpdatePasswordRequest request) {

        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundMemberException("해당 회원을 찾을 수 없습니다."));

        if (!passwordEncoder.matches(request.getCurrentPassword(), member.getPassword())) {
            throw new ValidatePasswordException("현재 비밀번호가 일치하지 않습니다.");
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new ValidatePasswordException("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }

        String encodePassword = passwordEncoder.encode(request.getNewPassword());
        member.updatePassword(encodePassword);

        Member save = memberRepository.save(member);

        return new UpdatePasswordResponse(save);
    }


    public LoginResponse login(String loginEmail, String password) {

        Optional<Member> memberOptional = memberRepository.findByLoginEmail(loginEmail);

        if (memberOptional.isEmpty()) {
            throw new NotFoundMemberException("해당 아이디를 찾을 수 없습니다.");
        }

        Member member = memberOptional.get();

        if (!member.getLoginEmail().equals(loginEmail)) {
            throw new ValidatePasswordException("아이디가 일치하지 않습니다.");
        }

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new ValidatePasswordException("비밀번호가 일치하지 않습니다.");
        }

        return new LoginResponse(member);
    }


    public void delete(Long id) {
        memberRepository.deleteById(id);
    }


    private void validateExistLoginId(JoinRequest request) {

        Optional<Member> findMembers = memberRepository.findByLoginEmail(request.getLoginEmail());
        if (findMembers.isPresent()) {
            throw new ExistMemberException("이미 존재하는 아이디입니다.");
        }
    }


    private void validateConfirmPassword(String password, String confirmPassword) {

        if (password == null || confirmPassword == null || !password.equals(confirmPassword)) {
            throw new ValidatePasswordException("비밀번호와 비밀번호 번호이 일치하지 않습니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String loginEmail) throws UsernameNotFoundException {

        Member member = memberRepository.findByLoginEmail(loginEmail)
                .orElseThrow(() -> new NotFoundMemberException("해당 회원을 찾을 수 없습니다."));

        if (member != null) {
            return new CustomUserDetails(member);
        }
        return null;
    }
}
