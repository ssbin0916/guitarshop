package com.project.guitarShop.controller;

import com.project.guitarShop.exception.board.NotFoundBoardException;
import com.project.guitarShop.exception.cart.NotFoundCartException;
import com.project.guitarShop.exception.comment.NotFoundCommentException;
import com.project.guitarShop.exception.item.NotFoundItemException;
import com.project.guitarShop.exception.member.ExistMemberException;
import com.project.guitarShop.exception.member.MemberUpdateFailException;
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
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(NotFoundOrderException.class)
    public ResponseEntity<String> handleNotFoundOrderException(NotFoundOrderException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(NotFoundItemException.class)
    public ResponseEntity<String> handleNotFoundItemException(NotFoundItemException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(NotFoundCartException.class)
    public ResponseEntity<String> handleNotFoundCartException(NotFoundCartException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(NotFoundBoardException.class)
    public ResponseEntity<String> handleNotFoundBoardException(NotFoundBoardException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(NotFoundCommentException.class)
    public ResponseEntity<String> handleNotFoundCommentException(NotFoundCommentException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(ExistMemberException.class)
    public ResponseEntity<String> handleExistMemberException(ExistMemberException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(ValidatePasswordException.class)
    public ResponseEntity<String> handleValidatePasswordException(ValidatePasswordException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(MemberUpdateFailException.class)
    public ResponseEntity<String> handleMemberUpdateFailException(MemberUpdateFailException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(OrderCancelException.class)
    public ResponseEntity<String> handleOrderCancelException(OrderCancelException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(InterruptedException.class)
    public ResponseEntity<String> handleInterruptedException(InterruptedException e) {
        Thread.currentThread().interrupt();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Thread interrupted");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다.");
    }

}
