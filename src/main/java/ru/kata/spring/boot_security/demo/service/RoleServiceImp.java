package ru.kata.spring.boot_security.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleServiceImp implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getRole(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException(String.format("Пользователь с Ролью %s не найден", name)));
    }

    @Override
    public Set<Role> checkRole(String role) {
        if (role.equals("ADMIN,USER")) {
            return Set.of(getRole("ADMIN"), getRole("USER"));
        } else {
            return Set.of(getRole(role));
        }
    }
}
