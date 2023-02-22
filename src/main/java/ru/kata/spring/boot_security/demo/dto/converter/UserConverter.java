package ru.kata.spring.boot_security.demo.dto.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dto.UserRequestDTO;
import ru.kata.spring.boot_security.demo.dto.UserResponseDTO;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;

@Service
@RequiredArgsConstructor
public class UserConverter implements Converter<UserRequestDTO, User> {

    private final RoleService roleService;

    @Override
    public User convert(UserRequestDTO source) {
        return new User()
                .setFirstName(source.getFirstName())
                .setLastName(source.getLastName())
                .setAge(source.getAge())
                .setEmail(source.getEmail())
                .setPassword(source.getPassword())
                .setRoles(roleService.checkRole(source.getRole()));
    }

    public UserResponseDTO entityToDTO(User source) {
        return new UserResponseDTO()
                .setId(source.getId())
                .setFirstName(source.getFirstName())
                .setLastName(source.getLastName())
                .setAge(source.getAge())
                .setEmail(source.getEmail())
                .setRole(source.getStringRoles());
    }
}
