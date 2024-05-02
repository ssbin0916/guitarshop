package com.project.guitarShop.repository.member;

import com.project.guitarShop.dto.member.MemberResponse;
import com.project.guitarShop.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByLoginId(String loginId);

    List<MemberResponse> findListByLoginId(String loginId);
}
