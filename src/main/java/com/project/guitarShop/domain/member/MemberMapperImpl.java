package com.project.guitarShop.domain.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MemberMapperImpl implements MemberMapper {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public List<Member> findAll() {
        return memberMapper.findAll();
    }

    @Override
    public Member findById(Long id) {
        return memberMapper.findById(id);
    }

    @Override
    public Optional<Member> findByMemberId(String memberId) {
        return memberMapper.findByMemberId(memberId);
    }

    @Override
    public void save(Member member) {
        memberMapper.save(member);
    }

    @Override
    public void update(Member member) {
        memberMapper.update(member);
    }

    @Override
    public void deleteById(Long id) {
        memberMapper.deleteById(id);
    }
}