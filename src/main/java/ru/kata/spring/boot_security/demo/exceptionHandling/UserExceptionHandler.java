package ru.kata.spring.boot_security.demo.exceptionHandling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException(UserNotFoundException e) {
        UserErrorResponse userErrorResponse = new UserErrorResponse("User with this id wasn't found!", System.currentTimeMillis());
        return new ResponseEntity<>(userErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException(Exception e) {
        UserErrorResponse userErrorResponse = new UserErrorResponse(e.getMessage(), System.currentTimeMillis());
        log.error("Error ", e);
        return ResponseEntity.internalServerError().body(userErrorResponse);
    }

}
