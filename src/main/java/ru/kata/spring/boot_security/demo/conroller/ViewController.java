package ru.kata.spring.boot_security.demo.conroller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ViewController {
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/viewadmin")
    public String viewAdmin() {
        return "index";
    }

    @GetMapping("/viewuser")
    public String viewUser() {
        return "user";
    }
}
