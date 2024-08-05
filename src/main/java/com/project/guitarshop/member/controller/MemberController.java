package com.project.guitarshop.member.controller;

import com.project.guitarshop.dto.member.MemberRequest.*;
import com.project.guitarshop.dto.member.MemberResponse.*;
import com.project.guitarshop.member.service.MemberService;
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

    @PutMapping("/updateInfo")
    public ResponseEntity<?> updateInfo(@Valid @RequestBody UpdateInfoRequest request) {
        UpdateInfoResponse response = memberService.updateInfo(request);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<Void> updatePassword(@Valid @RequestBody UpdatePasswordRequest request) {
        memberService.updatePassword(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteMember() {
        memberService.delete();
        return ResponseEntity.noContent().build();
    }
}