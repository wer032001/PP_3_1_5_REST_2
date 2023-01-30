package ru.kata.spring.boot_security.demo.conroller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/user/{email}")
    public String getAllUsers(@PathVariable String email, Model model) {
        model.addAttribute("user", userRepository.findByEmail(email).get());
        return "user";
    }

}
