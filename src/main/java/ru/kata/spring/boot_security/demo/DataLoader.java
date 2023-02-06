package ru.kata.spring.boot_security.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.save(new User(Long.valueOf(1), "Ivan", "Ivanov", 23, "admin@mail.ru", "admin", true, Set.of(Role.ADMIN)));
        userRepository.save(new User(Long.valueOf(2), "Marina", "Sidorova", 19, "user@mail.ru", "user", true, Set.of(Role.USER)));
    }
}

