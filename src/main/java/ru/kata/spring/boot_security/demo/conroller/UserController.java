package ru.kata.spring.boot_security.demo.conroller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.dto.UserResponseDTO;
import ru.kata.spring.boot_security.demo.service.UserService;

import static ru.kata.spring.boot_security.demo.configs.WebSecurityConfig.getPrincipal;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public UserResponseDTO getAllUsers() {
        return userService.getUserById(getPrincipal().getId());
    }
}
