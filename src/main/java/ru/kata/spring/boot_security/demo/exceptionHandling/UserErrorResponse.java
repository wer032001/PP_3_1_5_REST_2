package ru.kata.spring.boot_security.demo.exceptionHandling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserErrorResponse {
    private String messadge;
    private long timestamp;
}
