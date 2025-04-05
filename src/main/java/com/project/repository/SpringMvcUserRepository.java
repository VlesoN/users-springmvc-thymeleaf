package com.project.repository;

import com.project.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SpringMvcUserRepository implements UserRepository {
    private static List<User> users = new ArrayList<>();
    private static int idCounter = 1;

    static {
        users.add(new User(idCounter++, "admin", "admin123", "admin@gmail.com"));
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public void add(User user) {
        user.setId(idCounter++);
        users.add(user);
    }

    @Override
    public Optional<User> getUserById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    @Override
    public void delete(int id ) {
        users.removeIf(u -> u.getId() == id);
    }

    @Override
    public void update(User updatedUser) {
        Optional<User> user = getUserById(updatedUser.getId());
        user.ifPresent(u -> {
            u.setUsername(updatedUser.getUsername());
            u.setPassword(updatedUser.getPassword());
            u.setEmail(updatedUser.getEmail());
        });
    }
}
