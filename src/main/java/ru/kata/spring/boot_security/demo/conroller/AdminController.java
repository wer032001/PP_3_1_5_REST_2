package ru.kata.spring.boot_security.demo.conroller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImp;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Set;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private final UserService userService;
    private final RoleServiceImp roleServiceImp;

    @PostMapping("/admin")
    public String addUser(@ModelAttribute("user") User user, String role) {
        user.setRoles(Set.of(roleServiceImp.getRole(role)));
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.listUsers());
        model.addAttribute("user", new User());
        return "index";
    }

    @GetMapping("/admin/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String updateUserForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "updateuser";
    }

//    @PostMapping("/admin/{id}")
//    public String updateUser(@PathVariable Long id, @ModelAttribute("user") User user) {
//        userService.updateUser(user, id);
//        return "redirect:/admin";
//    }
}

