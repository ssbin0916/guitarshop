package com.project.guitarShop.controller.member;

import com.project.guitarShop.dto.member.MemberRequest.JoinRequest;
import com.project.guitarShop.dto.member.MemberRequest.UpdateInfoRequest;
import com.project.guitarShop.dto.member.MemberRequest.UpdatePasswordRequest;
import com.project.guitarShop.dto.member.MemberResponse.JoinResponse;
import com.project.guitarShop.dto.member.MemberResponse.UpdateInfoResponse;
import com.project.guitarShop.dto.member.MemberResponse.UpdatePasswordResponse;
import com.project.guitarShop.service.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@Valid @RequestBody JoinRequest request) {
        JoinResponse response = memberService.join(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}/updateInfo")
    public ResponseEntity<?> updateInfo(@PathVariable Long id, @Valid @RequestBody UpdateInfoRequest request) {
        UpdateInfoResponse response = memberService.updateInfo(id, request);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}/updatePassword")
    public ResponseEntity<?> updatePassword(@PathVariable Long id, @Valid @RequestBody UpdatePasswordRequest request) {
        UpdatePasswordResponse response = memberService.updatePassword(id, request);
        return ResponseEntity.ok().body(response);
    }

}
