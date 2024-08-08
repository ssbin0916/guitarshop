package com.project.guitarshop.member.service;

import com.project.guitarshop.member.dto.MemberRequest.*;
import com.project.guitarshop.member.dto.MemberResponse.*;

public interface MemberService {

    JoinResponse join(JoinRequest request);

    UpdateInfoResponse updateInfo(UpdateInfoRequest request);

    void updatePassword(UpdatePasswordRequest request);

    void delete();
}
