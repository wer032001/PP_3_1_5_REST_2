package ru.kata.spring.boot_security.demo.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;


    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Пользователь с id %s не найден", id)));
    }

    @Override
    public User updateUser(User user, Long id, String roles) {
        User updateUser = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Пользователь с id %s не найден", id)));

        if (user.getFirstName() != null) {
            updateUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            updateUser.setLastName(user.getLastName());
        }
        if (user.getAge() != 0) {
            updateUser.setAge(user.getAge());
        }
        if (user.getEmail() != null) {
            updateUser.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            updateUser.setPassword(user.getPassword());
        }
        if (roles != null) {
            updateUser.getRoles().remove(roleService.getRole(updateUser.getRoles().stream().findFirst().get().getName()));
            updateUser.getRoles().add(roleService.getRole(roles));
        }
        return userRepository.save(updateUser);
    }

    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Override
    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }
}
