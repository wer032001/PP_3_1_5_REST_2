package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.dto.UserRequestDTO;
import ru.kata.spring.boot_security.demo.dto.UserResponseDTO;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService {
    UserResponseDTO addUser(UserRequestDTO user);

    UserResponseDTO getUserById(Long id);

    User getUserByEmail(String email);

    List<UserResponseDTO> listUsers();

    void removeUser(Long id);

    UserResponseDTO updateUser(UserRequestDTO userRequestDTO, Long id);
}