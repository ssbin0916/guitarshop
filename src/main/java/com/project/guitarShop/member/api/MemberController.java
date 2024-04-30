package com.project.guitarShop.member.api;

import com.project.guitarShop.member.app.MemberRequest;
import com.project.guitarShop.member.app.MemberResponse;
import com.project.guitarShop.member.app.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<MemberResponseDTO> join(@RequestBody MemberRequestDTO memberRequestDTO) {

        MemberRequest memberRequest = MemberRequest.toRequest(memberRequestDTO);

        MemberResponse memberResponse = memberService.join(memberRequest);

        MemberResponseDTO memberResponseDTO = MemberResponseDTO.builder()
                .name(memberResponse.getName())
                .age(memberResponse.getAge())
                .phone(memberResponse.getPhone())
                .email(memberResponse.getEmail())
                .address(memberResponse.getAddress())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(memberResponseDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<MemberResponseDTO> login(@RequestBody MemberRequestDTO memberRequestDTO) {

        MemberRequest memberRequest = MemberRequest.toRequest(memberRequestDTO);

        MemberResponse memberResponse = memberService.login(memberRequest.loginId(), memberRequest.password());

        MemberResponseDTO memberResponseDTO = MemberResponseDTO.builder().build();
        return null;
    }
}
