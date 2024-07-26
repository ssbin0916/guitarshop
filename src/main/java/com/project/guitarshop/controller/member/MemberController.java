package com.project.guitarshop.controller.member;

import com.project.guitarshop.dto.member.MemberRequest.*;
import com.project.guitarshop.dto.member.MemberResponse.*;
import com.project.guitarshop.service.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@Valid @RequestBody JoinRequest request) {
        JoinResponse response = memberService.join(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/updateInfo/{id}")
    public ResponseEntity<?> updateInfo(@Valid @PathVariable Long id, @Valid @RequestBody UpdateInfoRequest request) {
        UpdateInfoResponse response = memberService.updateInfo(id, request);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/updatePassword/{id}")
    public ResponseEntity<String> updatePassword(@Valid @PathVariable Long id, @Valid @RequestBody UpdatePasswordRequest request) {
        memberService.updatePassword(id, request);
        return ResponseEntity.ok("비밀번호 변경 완료");
    }

}
