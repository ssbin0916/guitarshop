package com.project.guitarShop.member.app;

public interface MemberService {

    MemberResponse join(MemberRequest memberRequest);

    MemberResponse updateInfo(Long id, MemberResponse memberResponse);

    MemberResponse updatePassword(Long id, MemberResponse memberResponseㅈ);

    MemberResponse login(String loginId, String password);

    void delete(Long id);

}
