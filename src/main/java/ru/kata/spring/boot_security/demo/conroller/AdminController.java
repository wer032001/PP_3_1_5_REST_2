package ru.kata.spring.boot_security.demo.conroller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private final UserService userService;

    public static String mail;

    @PostMapping("/admin")
    public String addUser(@ModelAttribute("user") User user, HttpServletRequest httpServletRequest) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.listUsers());
        model.addAttribute("useremail", userService.getUserByEmail(mail));
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

    @PostMapping("/admin/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("user") User user) {
        userService.updateUser(user, id);
        return "redirect:/admin";
    }
    @GetMapping("/getOne")
    @ResponseBody
    public User getOne(Long id) {
        return userService.getUserById(id);
    }
}

