package com.project.guitarShop.member.repository;

import com.project.guitarShop.member.app.MemberResponse;
import com.project.guitarShop.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<MemberResponse> findByLoginId(String loginId);
    List<MemberResponse> findListByLoginId(String loginId);
}
