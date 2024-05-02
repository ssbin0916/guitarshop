//package com.project.guitarShop.member.api;
//
//import com.project.guitarShop.dto.member.MemberRequest;
//import com.project.guitarShop.dto.member.MemberResponse;
//import com.project.guitarShop.service.member.MemberService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api")
//@RequiredArgsConstructor
//public class MemberController {
//
//    private final MemberService memberService;
//
//    @PostMapping("/join")
//    public ResponseEntity<MemberResponseDto> join(@RequestBody MemberRequestDto memberRequestDTO) {
//
//        MemberRequest memberRequest = MemberRequest.toRequest(memberRequestDTO);
//
//        MemberResponse memberResponse = memberService.join(memberRequest);
//
//        MemberResponseDto memberResponseDTO = MemberResponseDto.builder()
//                .name(memberResponse.getName())
//                .age(memberResponse.getAge())
//                .phone(memberResponse.getPhone())
//                .email(memberResponse.getEmail())
//                .address(memberResponse.getAddress())
//                .build();
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(memberResponseDTO);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<MemberResponseDto> login(@RequestBody MemberRequestDto memberRequestDTO) {
//
//        MemberRequest memberRequest = MemberRequest.toRequest(memberRequestDTO);
//
//        MemberResponse memberResponse = memberService.login(memberRequest.loginId(), memberRequest.password());
//
//        MemberResponseDto memberResponseDTO = MemberResponseDto.builder().build();
//        return ResponseEntity.ok(memberResponseDTO);
//    }
//
////    @PostMapping("/login")
////    public ResponseEntity<MemberResponseDto> login(@RequestBody LoginRequest loginRequest) {
////
////        MemberResponse memberResponse = memberService.login(loginRequest.getLoginId(), loginRequest.getPassword());
////
////        MemberResponseDto memberResponseDTO = MemberResponseDto.builder()
////                .name(memberResponse.getName())
////                .age(memberResponse.getAge())
////                .phone(memberResponse.getPhone())
////                .email(memberResponse.getEmail())
////                .address(memberResponse.getAddress())
////                .build();
////
////        return ResponseEntity.ok(memberResponseDTO);
////    }
//
//    @PutMapping("/update")
//    public ResponseEntity<MemberResponseDto> update(@RequestBody MemberRequestDto memberRequestDTO) {
//        return null;
//    }
//}
