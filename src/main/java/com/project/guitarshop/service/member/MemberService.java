package com.project.guitarshop.service.member;

import com.project.guitarshop.dto.member.MemberRequest.*;
import com.project.guitarshop.dto.member.MemberResponse.*;

public interface MemberService {

    JoinResponse join(JoinRequest request);

    UpdateInfoResponse updateInfo(Long id, UpdateInfoRequest request);

    void updatePassword(Long id, UpdatePasswordRequest request);

    LoginResponse login(String loginEmail, String password);

    void delete(Long id);
}
