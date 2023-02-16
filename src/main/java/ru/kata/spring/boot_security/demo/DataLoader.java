package ru.kata.spring.boot_security.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        userRepository.save(new User(1L, "Ivan", "Ivanov", 23, "admin@mail.ru", passwordEncoder.encode("admin"), true, Set.of(new Role(1L, "ADMIN"), new Role(2L, "USER"))));
        userRepository.save(new User(2L, "Marina", "Sidorova", 19, "user@mail.ru", passwordEncoder.encode("user"), true, Set.of(new Role(2L, "USER"))));
    }
}

