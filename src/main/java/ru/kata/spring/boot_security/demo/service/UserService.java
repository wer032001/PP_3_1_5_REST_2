package ru.kata.spring.boot_security.demo.service;



import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    User getUserById(Long id);

    User updateUser(User user, Long id);

    List<User> listUsers();

    void removeUser(Long id);
}