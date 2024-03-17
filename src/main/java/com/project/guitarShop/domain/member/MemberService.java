package com.project.guitarShop.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    @Transactional(readOnly = true)
    public List<Member> findAll() {
        return memberMapper.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(memberMapper.findById(id));
    }

    @Transactional
    public void save(Member member) {
        memberMapper.save(member);
    }

    @Transactional
    public void update(Member member) {
        memberMapper.update(member);
    }

    @Transactional
    public void delete(Long id) {
        memberMapper.deleteById(id);
    }
}
