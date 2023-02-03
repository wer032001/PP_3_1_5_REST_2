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
        userRepository.save(new User(Long.valueOf(1), "Ivan", "Ivanov", 23, "asd@asd", "111", true, Set.of(Role.ADMIN)));
        userRepository.save(new User(Long.valueOf(2), "Ivan", "Ivanov", 23, "qwe@asd", "111", true, Set.of(Role.USER)));
        userRepository.save(new User(Long.valueOf(3), "Ivan", "Ivanov", 23, "cvb@asd", "111", true, Set.of(Role.USER)));
        userRepository.save(new User(Long.valueOf(4), "Ivan", "Ivanov", 23, "hjk@asd", "111", true, Set.of(Role.USER)));
        userRepository.save(new User(Long.valueOf(5), "Ivan", "Ivanov", 23, "acv@asd", "111", true, Set.of(Role.USER)));
    }
}

