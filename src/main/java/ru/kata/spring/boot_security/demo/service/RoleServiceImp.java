package ru.kata.spring.boot_security.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleServiceImp {
    private final RoleRepository roleRepository;

    public Role getRole(String name) {
        return roleRepository.findByName(name).get();
    }
}
