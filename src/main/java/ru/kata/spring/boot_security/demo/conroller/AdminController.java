package ru.kata.spring.boot_security.demo.conroller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Set;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public static String mail;

    @GetMapping("/admin")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.listUsers());
        model.addAttribute("useremail", userService.getUserByEmail(mail));
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/admin")
    public String addUser(@ModelAttribute("user") User user, String role) {
        user.setRoles(Set.of(roleService.getRole(role)));
        userService.addUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }


    @GetMapping("/admin/getOne")
    @ResponseBody
    public User getOne(Long id) {
        return userService.getUserById(id);
    }

}

