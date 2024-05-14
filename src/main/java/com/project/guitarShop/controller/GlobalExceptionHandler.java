package com.project.guitarShop.controller;

import com.project.guitarShop.exception.cart.NotFoundCartException;
import com.project.guitarShop.exception.item.NotFoundItemException;
import com.project.guitarShop.exception.member.ExistMemberException;
import com.project.guitarShop.exception.member.NotFoundMemberException;
import com.project.guitarShop.exception.member.ValidatePasswordException;
import com.project.guitarShop.exception.order.NotFoundOrderException;
import com.project.guitarShop.exception.order.OrderCancelException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundMemberException.class)
    public ResponseEntity<String> handleNotFoundMemberException(NotFoundMemberException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 멤버를 찾을 수 없습니다.");
    }

    @ExceptionHandler(NotFoundOrderException.class)
    public ResponseEntity<String> handleNotFoundOrderException(NotFoundOrderException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 주문을 찾을 수 없습니다.");
    }

    @ExceptionHandler(NotFoundItemException.class)
    public ResponseEntity<String> handleNotFoundItemException(NotFoundItemException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 상품을 찾을 수 없습니다.");
    }


    @ExceptionHandler(NotFoundCartException.class)
    public ResponseEntity<String> handleNotFoundCartException(NotFoundCartException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 장바구니를 찾을 수 없습니다.");
    }

    @ExceptionHandler(ExistMemberException.class)
    public ResponseEntity<String> handleExistMemberException(ExistMemberException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 존재하는 회원입니다.");
    }

    @ExceptionHandler(ValidatePasswordException.class)
    public ResponseEntity<String> handleValidatePasswordException(ValidatePasswordException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비밀번호가 일치하지 않습니다.");
    }

    @ExceptionHandler(OrderCancelException.class)
    public ResponseEntity<String> handleOrderCancelException(OrderCancelException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 취소 되었거나 배송된 주문은 취소할 수 없습니다.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다.");
    }

}
