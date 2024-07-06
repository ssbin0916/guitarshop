package com.project.guitarShop.service.member;

import com.project.guitarShop.dto.member.MemberRequest.*;
import com.project.guitarShop.dto.member.MemberResponse.*;
import com.project.guitarShop.entity.member.Member;
import com.project.guitarShop.exception.member.ExistMemberException;
import com.project.guitarShop.exception.member.NotFoundMemberException;
import com.project.guitarShop.exception.member.ValidatePasswordException;
import com.project.guitarShop.repository.member.MemberRepository;
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
                .role("USER")
                .build();

        memberRepository.save(member);

        return new JoinResponse(member);
    }

    @Transactional
    @Override
    public UpdateInfoResponse updateInfo(Long id, UpdateInfoRequest request) {

        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundMemberException("해당 회원을 찾을 수 없습니다."));

        boolean isUpdated = false;
        StringBuilder errorMessages = new StringBuilder();

        if (request.phone() != null && !request.phone().isEmpty()) {
            member.updatePhone(request.phone());
            isUpdated = true;
        }

        if (request.address() != null) {
            member.updateAddress(request.address());
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


    @Transactional
    @Override
    public void updatePassword(Long id, UpdatePasswordRequest request) {

        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundMemberException("해당 회원을 찾을 수 없습니다."));

        if (!passwordEncoder.matches(request.currentPassword(), member.getPassword())) {
            throw new ValidatePasswordException("현재 비밀번호가 일치하지 않습니다.");
        }

        if (!request.newPassword().equals(request.confirmPassword())) {
            throw new ValidatePasswordException("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }

        String encodePassword = passwordEncoder.encode(request.newPassword());
        member.updatePassword(encodePassword);

        memberRepository.save(member);

    }

    @Transactional
    @Override
    public LoginResponse login(String loginEmail, String password) {

        Member member = memberRepository.findByLoginEmail(loginEmail)
                .orElseThrow(() -> new NotFoundMemberException("해당 아이디를 찾을 수 없습니다."));

        if (!member.getLoginEmail().equals(loginEmail)) {
            throw new ValidatePasswordException("아이디가 일치하지 않습니다.");
        }

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new ValidatePasswordException("비밀번호가 일치하지 않습니다.");
        }

        return new LoginResponse(member);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }


    private void validateExistLoginId(JoinRequest request) {

        Optional<Member> findMembers = memberRepository.findByLoginEmail(request.loginEmail());
        if (findMembers.isPresent()) {
            throw new ExistMemberException("이미 존재하는 아이디입니다.");
        }
    }


    private void validateConfirmPassword(String password, String confirmPassword) {

        if (password == null || !password.equals(confirmPassword)) {
            throw new ValidatePasswordException("비밀번호와 비밀번호 번호이 일치하지 않습니다.");
        }
    }

}
