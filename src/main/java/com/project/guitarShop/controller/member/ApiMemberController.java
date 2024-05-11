package com.project.guitarShop.controller.member;

import com.project.guitarShop.dto.member.MemberRequest.JoinRequest;
import com.project.guitarShop.dto.member.MemberRequest.LoginRequest;
import com.project.guitarShop.dto.member.MemberRequest.UpdateInfoRequest;
import com.project.guitarShop.dto.member.MemberRequest.UpdatePasswordRequest;
import com.project.guitarShop.dto.member.MemberResponse.JoinResponse;
import com.project.guitarShop.dto.member.MemberResponse.LoginResponse;
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
public class ApiMemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody @Valid JoinRequest request) {

        try {
            JoinResponse response = memberService.join(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("서버 오류가 발생했습니다.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {

        try {
            LoginResponse response = memberService.login(request.getLoginEmail(), request.getPassword());
            return ResponseEntity.ok().body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("서버 오류가 발생했습니다.");
        }
    }

    @PutMapping("/{id}/updateInfo")
    public ResponseEntity<?> updateInfo(@PathVariable Long id, @RequestBody @Valid UpdateInfoRequest request) {

        try {
            memberService.updateInfo(id, request);
            StringBuilder message = new StringBuilder("변경 완료: ");

            if (request.getPhone() != null) {
                message.append("전화번호 ");
            }

            if (request.getAddress() != null) {
                message.append("주소 ");
            }

            if (message.toString().equals("변경 완료: ")) {
                message = new StringBuilder("변경된 정보가 없습니다.");
            }
            return ResponseEntity.ok(message.toString());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("서버 오류가 발생했습니다.");
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updatePassword(@PathVariable Long id, @Valid @RequestBody UpdatePasswordRequest request) {

        try {
            UpdatePasswordResponse response = memberService.updatePassword(id, request);
            return ResponseEntity.ok().body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("비밀번호 변경에 실패했습니다.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("서버 오류가 발생했습니다.");
        }
    }
}
