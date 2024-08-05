package com.project.guitarshop.member.repository;

import com.project.guitarshop.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByLoginEmail(String loginEmail);
}
