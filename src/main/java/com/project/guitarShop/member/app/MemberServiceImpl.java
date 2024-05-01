package com.project.guitarShop.member.app;

import com.project.guitarShop.exception.ExistMemberException;
import com.project.guitarShop.exception.NotFoundMemberException;
import com.project.guitarShop.exception.ValidatePasswordException;
import com.project.guitarShop.member.domain.Member;
import com.project.guitarShop.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public MemberResponse join(MemberRequest memberRequest) {

        validateExistLoginId(memberRequest);
        validateConfirmPassword(memberRequest.getPassword(), memberRequest.getConfirmPassword());

        Member member = Member.toDomain(memberRequest);

        memberRepository.save(member);

        return MemberResponse.builder()
                .loginId(member.getLoginId())
                .name(member.getName())
                .age(member.getAge())
                .phone(member.getPhone())
                .email(member.getEmail())
                .address(member.getAddress())
                .role(member.getRole())
                .build();
    }

    @Override
    public MemberResponse updateInfo(Long id, MemberRequest memberRequest) {
        Member member = memberRepository.findById(id).orElseThrow(NotFoundMemberException::new);

        memberRepository.save(member);

        return MemberResponse.builder()
                .phone(member.getPhone())
                .email(member.getEmail())
                .address(member.getAddress())
                .build();
    }

    @Override
    public MemberResponse updatePassword(Long id, MemberRequest memberRequest) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new NotFoundMemberException("찾을 수 없는 회원입니다."));

        validateConfirmPassword(memberRequest.getPassword(), memberRequest.getConfirmPassword());

        memberRequest.getPassword();
        memberRequest.getConfirmPassword();

        memberRepository.save(member);

        return MemberResponse.builder()
                .build();
    }

    @Override
    public MemberResponse login(String loginId, String password) {
        Optional<Member> memberOptional = memberRepository.findByLoginId(loginId);

        if (memberOptional.isPresent() && memberOptional.get().getPassword().equals(password)) {
            Member member = memberOptional.get();
            return MemberResponse.builder()
                    .loginId(member.getLoginId())
                    .name(member.getName())
                    .age(member.getAge())
                    .phone(member.getPhone())
                    .email(member.getEmail())
                    .role(member.getRole())
                    .address(member.getAddress())
                    .build();
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

    //--검증 메서드--//
    private void validateExistLoginId(MemberRequest memberRequest) {
        List<MemberResponse> findMembers = memberRepository.findListByLoginId(memberRequest.getLoginId());
        if (!findMembers.isEmpty()) {
            throw new ExistMemberException("이미 존재하는 아이디입니다.");
        }
    }

    private void validateConfirmPassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new ValidatePasswordException("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }
    }
}
