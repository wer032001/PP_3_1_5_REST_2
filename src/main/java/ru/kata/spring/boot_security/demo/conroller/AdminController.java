package ru.kata.spring.boot_security.demo.conroller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dto.UserRequestDTO;
import ru.kata.spring.boot_security.demo.dto.UserResponseDTO;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
//@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private final UserService userService;


    @GetMapping
    public List<UserResponseDTO> getUsers() {
        return userService.listUsers();
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUser(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.removeUser(id);
    }

    @PostMapping
    public UserResponseDTO addUser(@RequestBody UserRequestDTO user) {
        return userService.addUser(user);
    }

//    @GetMapping("/admin/getOne")
//    @ResponseBody
//    public User getOne(Long id) {
//        return userService.getUserById(id);
//  }

    @PatchMapping("/admin")
    public String updateUser(@ModelAttribute("user") User user, String editRole) {
        userService.updateUser(user, user.getId(), editRole);
        return "redirect:/admin";
    }

}

