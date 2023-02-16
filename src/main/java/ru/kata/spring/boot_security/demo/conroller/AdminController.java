package ru.kata.spring.boot_security.demo.conroller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import static ru.kata.spring.boot_security.demo.configs.WebSecurityConfig.getPrincipal;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private final UserService userService;

    @GetMapping("/admin")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.listUsers());
        model.addAttribute("useremail", userService.getUserByEmail(getPrincipal().getEmail()));
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/admin")
    public String addUser(@ModelAttribute("user") User user, String role) {
        userService.addUser(user, role);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.removeUser(id);
        if (id.equals(getPrincipal().getId())) {
            return "redirect:/logout";
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/getOne")
    @ResponseBody
    public User getOne(Long id) {
        return userService.getUserById(id);
    }

    @PatchMapping("/admin")
    public String updateUser(@ModelAttribute("user") User user, String editRole) {
        userService.updateUser(user, user.getId(), editRole);
        return "redirect:/admin";
    }

}

