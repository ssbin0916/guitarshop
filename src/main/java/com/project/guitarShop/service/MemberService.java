package com.project.guitarShop.service;

import com.project.guitarShop.domain.member.Member;
import com.project.guitarShop.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    @Transactional
    public void save(Member member) {
        validateDuplicateMember(member);
        memberMapper.save(member);
    }

    @Transactional(readOnly = true)
    public List<Member> findMembers() {
        return memberMapper.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Member> findById(Long id) {
        return memberMapper.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Member> findByLoginId(String loginId) {
        return memberMapper.findByLoginId(loginId);
    }

    @Transactional(readOnly = true)
    public Optional<Member> findByName(String name) {
        return memberMapper.findByName(name);
    }

    @Transactional
    public void update(Member member) {
        validateMemberExist(member.getId());
        memberMapper.update(member);
    }

    @Transactional
    public void delete(Long id) {
        validateMemberExist(id);
        memberMapper.delete(id);
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> findMembers = memberMapper.findByLoginId(member.getLoginId());
        if (findMembers.isPresent()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    private void validateMemberExist(Long id) {
        Optional<Member> member = memberMapper.findById(id);
        if (member.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }
    }
}
