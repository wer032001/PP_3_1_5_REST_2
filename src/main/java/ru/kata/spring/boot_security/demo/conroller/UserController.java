package ru.kata.spring.boot_security.demo.conroller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import static ru.kata.spring.boot_security.demo.configs.WebSecurityConfig.getPrincipal;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public String getAllUsers(Model model) {
        model.addAttribute("user", userService.getUserByEmail(getPrincipal().getEmail()));
        return "user";
    }
}
