package com.project.guitarShop.repository.member;

import com.project.guitarShop.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByLoginEmail(String loginEmail);
}
