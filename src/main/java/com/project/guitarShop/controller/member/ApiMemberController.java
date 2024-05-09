package com.project.guitarShop.controller.member;

import com.project.guitarShop.dto.member.MemberRequest.JoinRequest;
import com.project.guitarShop.dto.member.MemberRequest.LoginRequest;
import com.project.guitarShop.dto.member.MemberRequest.UpdatePasswordRequest;
import com.project.guitarShop.dto.member.MemberResponse.JoinResponse;
import com.project.guitarShop.dto.member.MemberResponse.LoginResponse;
import com.project.guitarShop.service.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiMemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<JoinResponse> join(@RequestBody @Valid JoinRequest request) {

        JoinResponse response = memberService.join(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {

        LoginResponse response = memberService.login(request.getLoginEmail(), request.getPassword());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<String> updatePassword(@PathVariable Long id, @RequestBody @Valid UpdatePasswordRequest request) {

        memberService.updatePassword(id, request);

        return ResponseEntity.ok("변경 완료");
    }
}
