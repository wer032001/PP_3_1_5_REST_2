package ru.kata.spring.boot_security.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dto.UserRequestDTO;
import ru.kata.spring.boot_security.demo.dto.UserResponseDTO;
import ru.kata.spring.boot_security.demo.dto.converter.UserConverter;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserConverter userConverter;

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO addUser(UserRequestDTO user) {
        String encode = passwordEncoder.encode(user.getPassword());
       return userConverter.entityToDTO(userRepository.save(userConverter.convert(user.setPassword(encode))));
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        return userConverter.entityToDTO(userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Пользователь с id %s не найден", id))));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException(String.format("Пользователь с email %s не найден", email)));
    }

    @Override
    public List<UserResponseDTO> listUsers() {
        return userRepository.findAll().stream().map(userConverter::entityToDTO).toList();
    }

    @Override
    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(User user, Long id, String roles) {
//        addUser(user, roles);
    }
}
