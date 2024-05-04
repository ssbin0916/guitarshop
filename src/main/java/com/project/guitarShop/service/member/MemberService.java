package com.project.guitarShop.service.member;

import com.project.guitarShop.domain.member.Member;
import com.project.guitarShop.dto.member.MemberRequest.JoinRequest;
import com.project.guitarShop.dto.member.MemberRequest.UpdateInfoRequest;
import com.project.guitarShop.dto.member.MemberRequest.UpdatePasswordRequest;
import com.project.guitarShop.dto.member.MemberResponse.JoinResponse;
import com.project.guitarShop.dto.member.MemberResponse.LoginResponse;
import com.project.guitarShop.exception.ExistMemberException;
import com.project.guitarShop.exception.NotFoundMemberException;
import com.project.guitarShop.exception.ValidatePasswordException;
import com.project.guitarShop.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public JoinResponse join(JoinRequest request) {
        validateExistLoginId(request);
        validateConfirmPassword(request.getPassword(), request.getConfirmPassword());

        Member save = memberRepository.save(request.toDomain(passwordEncoder));

        return new JoinResponse(save);
    }

    public void updateInfo(Long id, UpdateInfoRequest request) {
        Member existingMember = memberRepository.findById(id).orElseThrow(() -> new NotFoundMemberException("찾을 수 없는 회원입니다."));

        existingMember.updateInfo(request.getPhone(), request.getEmail(), request.getAddress());

        memberRepository.save(existingMember);
    }

    public void updatePassword(Long id, UpdatePasswordRequest request, BCryptPasswordEncoder passwordEncoder) {
        Member existingMember = memberRepository.findById(id).orElseThrow(() -> new NotFoundMemberException("찾을 수 없는 회원입니다."));

        existingMember.updatePassword(request.getPassword(), request.getConfirmPassword(), passwordEncoder);

        validateConfirmPassword(request.getPassword(), request.getConfirmPassword());

        memberRepository.save(existingMember);
    }

    public LoginResponse login(String loginId, String password) {
        Optional<Member> memberOptional = memberRepository.findByLoginId(loginId);

        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            return new LoginResponse(member);
        }
        throw new ValidatePasswordException("로그인에 실패했습니다.");
    }

    public void delete(Long id) {
        memberRepository.deleteById(id);
    }




    //--검증 메서드--//
    private void validateExistLoginId(JoinRequest request) {
        Optional<Member> findMembers = memberRepository.findByLoginId(request.getLoginId());
        if (findMembers.isPresent()) {
            throw new ExistMemberException("이미 존재하는 아이디입니다.");
        }
    }

    private void validateConfirmPassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new ValidatePasswordException("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }
    }
}
