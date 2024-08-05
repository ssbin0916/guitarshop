package com.project.guitarshop.global.exception;

import com.project.guitarshop.board.exception.NotFoundPostException;
import com.project.guitarshop.cart.exception.NotFoundCartException;
import com.project.guitarshop.board.exception.NotFoundReplyException;
import com.project.guitarshop.item.exception.NotFoundItemException;
import com.project.guitarshop.member.exception.ExistMemberException;
import com.project.guitarshop.member.exception.NotFoundMemberException;
import com.project.guitarshop.member.exception.ValidatePasswordException;
import com.project.guitarshop.order.exception.NotFoundOrderException;
import com.project.guitarshop.order.exception.OrderCancelException;
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

    @ExceptionHandler(NotFoundPostException.class)
    public ResponseEntity<String> handleNotFoundPostException(NotFoundPostException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(NotFoundReplyException.class)
    public ResponseEntity<String> handleNotFoundReplyException(NotFoundReplyException e) {
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
