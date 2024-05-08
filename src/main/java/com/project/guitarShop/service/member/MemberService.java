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

        String gender = calculateGender(request.getRrn());
        Member member = Member.builder()
                .loginId(request.getLoginId())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .rrn(request.getRrn())
                .gender(gender)
                .phone(request.getPhone())
                .email(request.getEmail())
                .address(request.getAddress())
                .build();

        memberRepository.save(member);
        return new JoinResponse(member);
    }

    public void updateInfo(Long id, UpdateInfoRequest request) {
        Member existingMember = memberRepository.findById(id).orElseThrow(() -> new NotFoundMemberException("찾을 수 없는 회원입니다."));

        existingMember.updateInfo(request.getPhone(), request.getEmail(), request.getAddress());

        memberRepository.save(existingMember);
    }

    public void updatePassword(Long id, UpdatePasswordRequest request, BCryptPasswordEncoder passwordEncoder) {
        Member existingMember = memberRepository.findById(id).orElseThrow(() -> new NotFoundMemberException("찾을 수 없는 회원입니다."));

        existingMember.updatePassword(request.getPassword(), passwordEncoder);

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


    private void validateExistLoginId(JoinRequest request) {
        Optional<Member> findMembers = memberRepository.findByLoginId(request.getLoginId());
        if (findMembers.isPresent()) {
            throw new ExistMemberException("이미 존재하는 아이디입니다.");
        }
    }

    private void validateConfirmPassword(String password, String confirmPassword) {
        if (password == null || confirmPassword == null || !password.equals(confirmPassword)) {
            throw new ValidatePasswordException("비밀번호와 비밀번호 번호이 일치하지 않습니다.");
        }
    }

    private String calculateGender(String rrn) {
        char gender = rrn.charAt(7);
        switch (gender) {
            case '1':
            case '3':
                return "남자";
            case '2':
            case '4':
                return "여자";
            default:
                throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }
}
