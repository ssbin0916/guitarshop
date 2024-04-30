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
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public MemberResponse join(MemberRequest memberRequest) {

        validateMember(memberRequest);
        validateConfirmPassword(memberRequest.password(), memberRequest.confirmPassword());

        Member member = Member.toDomain(memberRequest);

        memberRepository.save(member);

        return MemberResponse.builder()
                .loginId(member.getLoginId())
                .password(bCryptPasswordEncoder.encode(member.getPassword()))
                .confirmPassword(bCryptPasswordEncoder.encode(member.getConfirmPassword()))
                .name(member.getName())
                .age(member.getAge())
                .phone(member.getPhone())
                .email(member.getEmail())
                .address(member.getAddress())
                .role(member.getRole())
                .build();
    }

    @Override
    @Transactional
    public MemberUpdateDTO updateInfo(Long id, MemberUpdateDTO memberUpdateDTO) {
        Member member = memberRepository.findById(id).orElseThrow(NotFoundMemberException::new);

        if (memberUpdateDTO.getPhone() != null || memberUpdateDTO.getEmail() != null || memberUpdateDTO.getAddress() != null) {
            member.updateInfo(memberUpdateDTO.getPhone(), memberUpdateDTO.getEmail(), memberUpdateDTO.getAddress());
        }

        memberRepository.save(member);

        return MemberUpdateDTO.builder()
                .phone(member.getPhone())
                .email(member.getEmail())
                .address(member.getAddress())
                .build();
    }

    @Override
    @Transactional
    public MemberUpdatePasswordDTO updatePassword(Long id, MemberUpdatePasswordDTO memberUpdatePasswordDTO) {
        Member member = memberRepository.findById(id).orElseThrow(NotFoundMemberException::new);

        validateConfirmPassword(memberUpdatePasswordDTO.getPassword(), memberUpdatePasswordDTO.getConfirmPassword());

        member.updatePassword(memberUpdatePasswordDTO.getPassword(), memberUpdatePasswordDTO.getConfirmPassword());

        memberRepository.save(member);

        return MemberUpdatePasswordDTO.builder()
                .password(member.getPassword())
                .confirmPassword(memberUpdatePasswordDTO.getConfirmPassword())
                .build();
    }

    @Override
    @Transactional
    public MemberResponse login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }


    public Optional<Member> findOne(Long id) {
        return memberRepository.findById(id);
    }

    public Optional<Member> findByLoginId(String loginId) {
        return null;
    }

    private void validateMember(MemberRequest memberRequest) {
        List<MemberResponse> findMembers = memberRepository.findListByLoginId(memberRequest.loginId());
        if (!findMembers.isEmpty()) {
            throw new ExistMemberException("이미 존재하는 회원입니다.");
        }
    }

    private void validateConfirmPassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new ValidatePasswordException("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }
    }
}
