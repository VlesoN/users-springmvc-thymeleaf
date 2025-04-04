package com.project.repository;

import com.project.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> getAllUsers();

    void add(User user);

    Optional<User> getUserById(int id);

    void delete(int id);

    void update(User user);
}
