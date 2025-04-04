package com.project.service;

import com.project.models.User;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public void add(User user) {
        userRepository.add(user);
    }

    public Optional<User> getUserById(int id) {
        return userRepository.getUserById(id);
    }

    public void delete(int id) {
        userRepository.delete(id);
    }

    public void update(User updatedUser) {
        userRepository.update(updatedUser);
    }
}
