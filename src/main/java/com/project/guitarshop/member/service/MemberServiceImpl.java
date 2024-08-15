package com.project.guitarshop.member.service;

import com.project.guitarshop.member.dto.MemberRequest.*;
import com.project.guitarshop.member.dto.MemberResponse.*;
import com.project.guitarshop.member.entity.Member;
import com.project.guitarshop.member.exception.ExistMemberException;
import com.project.guitarshop.member.exception.NotFoundMemberException;
import com.project.guitarshop.member.exception.ValidatePasswordException;
import com.project.guitarshop.member.repository.MemberRepository;
import com.project.guitarshop.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public JoinResponse join(JoinRequest request) {
        validateExistLoginId(request);
        validateConfirmPassword(request.password(), request.confirmPassword());

        Member member = Member.builder()
                .loginEmail(request.loginEmail())
                .password(passwordEncoder.encode(request.password()))
                .name(request.name())
                .phone(request.phone())
                .address(request.address())
                .role("ROLE_USER")
                .build();

        memberRepository.save(member);

        return new JoinResponse(member);
    }

    @Transactional
    @Override
    public UpdateInfoResponse updateInfo(UpdateInfoRequest request) {

        Long memberId = SecurityUtil.getCurrentUserId();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundMemberException("해당 회원을 찾을 수 없습니다."));

        boolean isUpdated = false;

        if (request.phone() != null && !request.phone().isEmpty()) {
            member.updatePhone(request.phone());
            isUpdated = true;
        }

        if (request.address() != null) {
            member.updateAddress(request.address());
            isUpdated = true;
        }

        if (!isUpdated) {
            throw new IllegalArgumentException("변경할 정보가 없습니다.");
        }

        return new UpdateInfoResponse(member);
    }


    @Transactional
    @Override
    public void updatePassword(UpdatePasswordRequest request) {

        Long memberId = SecurityUtil.getCurrentUserId();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundMemberException("해당 회원을 찾을 수 없습니다."));

        if (!passwordEncoder.matches(request.currentPassword(), member.getPassword())) {
            throw new ValidatePasswordException("현재 비밀번호가 일치하지 않습니다.");
        }

        if (!request.newPassword().equals(request.confirmPassword())) {
            throw new ValidatePasswordException("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }

        String encodePassword = passwordEncoder.encode(request.newPassword());
        member.updatePassword(encodePassword);
    }

    @Transactional
    @Override
    public void delete() {

        Long memberId = SecurityUtil.getCurrentUserId();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundMemberException("해당 회원을 찾을 수 없습니다."));

        memberRepository.delete(member);
    }

    private void validateExistLoginId(JoinRequest request) {

        Optional<Member> findMembers = memberRepository.findByLoginEmail(request.loginEmail());
        if (findMembers.isPresent()) {
            throw new ExistMemberException("이미 존재하는 아이디입니다.");
        }
    }


    private void validateConfirmPassword(String password, String confirmPassword) {

        if (password == null || !password.equals(confirmPassword)) {
            throw new ValidatePasswordException("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }
    }

}
