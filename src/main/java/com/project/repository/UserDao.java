package com.project.repository;

import com.project.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserDao(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void add(User user) {
        user.setRole("ROLE_USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }

    public void update(User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(updatedUser.getId());

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());

            // Только если пароль был изменён — перекодировать
            if (!updatedUser.getPassword().equals(existingUser.getPassword())) {
                existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }

            userRepository.save(existingUser);
        } else {
            throw new IllegalArgumentException("User not found");
        }

    }

    public Optional<User> findById(int id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            logger.warn("Пользователь с ID {} не найден", id);
        }

        return user;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
