package ru.kata.spring.boot_security.demo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserResponseDTO {

    private Long id;
    private String firstName;

    private String lastName;

    private int age;

    private String email;

    private String password;

    private String role;
}
