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

import java.util.ArrayList;
import java.util.List;

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

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = memberService.login(request.getLoginEmail(), request.getPassword());
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}/updateInfo")
    public ResponseEntity<?> updateInfo(@PathVariable Long id, @Valid @RequestBody UpdateInfoRequest request) {

        try {
            memberService.updateInfo(id, request);
            List<String> updateMessage = new ArrayList<>();

            if (request.getPhone() != null) {
                updateMessage.add("전화번호");
            }

            if (request.getAddress() != null) {
                updateMessage.add("주소");
            }

            if (updateMessage.isEmpty()) {
                return ResponseEntity.ok("변경된 정보가 없습니다.");
            } else {
                String message = String.join("", updateMessage) + "가 변경되었습니다.";
                return ResponseEntity.ok(message);
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("회원 정보 수정이 실패했습니다.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("서버 오류가 발생했습니다.");
        }
    }

    @PutMapping("/{id}/updatePassword")
    public ResponseEntity<?> updatePassword(@PathVariable Long id, @Valid @RequestBody UpdatePasswordRequest request) {
        UpdatePasswordResponse response = memberService.updatePassword(id, request);
        return ResponseEntity.ok().body(response);
    }

}
