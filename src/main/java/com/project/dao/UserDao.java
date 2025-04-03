package com.project.dao;

import com.project.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserDao {
    private static List<User> users = new ArrayList<>();
    private static int idCounter = 1;

    static {
        users.add(new User(idCounter++,"admin","admin123","admin@gmail.com"));
    }

    public List<User> getAllUsers() {
      return users;
    }

    public void add(User user) {
        user.setId(idCounter++);
        users.add(user);
    }

    public Optional<User> getUserById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }
    public void delete(int id,User user) {
        user.setId(id);
        users.removeIf(u -> u.getId() == user.getId());
    }

    public void update(int id, User updatedUser) {
        Optional<User> user = getUserById(id);
        user.ifPresent(u -> {
            u.setUsername(updatedUser.getUsername());
            u.setPassword(updatedUser.getPassword());
            u.setEmail(updatedUser.getEmail());
        });
    }
}
