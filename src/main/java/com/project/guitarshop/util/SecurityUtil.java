package com.project.guitarshop.util;

import com.project.guitarshop.member.dto.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    private SecurityUtil() {
    }

    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            return ((CustomUserDetails) authentication.getPrincipal()).getId();
        }
        throw new IllegalStateException("사용자가 인증되지 않았습니다.");
    }
}
