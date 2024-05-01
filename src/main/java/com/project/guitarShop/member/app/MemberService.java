package com.project.guitarShop.member.app;

public interface MemberService {

    MemberResponse join(MemberRequest memberRequest);

    MemberResponse updateInfo(Long id, MemberRequest memberRequest);

    MemberResponse updatePassword(Long id, MemberRequest memberRequest);

    MemberResponse login(String loginId, String password);

    void delete(Long id);

}
