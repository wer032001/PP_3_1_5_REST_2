package ru.kata.spring.boot_security.demo.conroller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.dto.UserResponseDTO;
import ru.kata.spring.boot_security.demo.service.UserService;

import static ru.kata.spring.boot_security.demo.configs.WebSecurityConfig.getPrincipal;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public UserResponseDTO getUser() {
        return userService.getUserById(getPrincipal().getId());
    }
}
