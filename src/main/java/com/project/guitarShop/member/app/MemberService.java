package com.project.guitarShop.member.app;

public interface MemberService {

    MemberResponse join(MemberRequest memberRequest);

    MemberUpdateDTO updateInfo(Long id, MemberUpdateDTO memberUpdateDTO);

    MemberUpdatePasswordDTO updatePassword(Long id, MemberUpdatePasswordDTO memberUpdatePasswordDTO);

    MemberResponse login(String loginId, String password);

    void delete(Long id);

}
