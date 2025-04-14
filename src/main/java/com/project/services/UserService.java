package com.project.services;

import com.project.models.User;
import com.project.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void add(User user) {
        userDao.add(user);
    }

    public Optional<User> getUserById(int id) {
        return userDao.getUserById(id);
    }

    public void delete(int id) {
        userDao.delete(id);
    }

    public void update(User updatedUser) {
        userDao.update(updatedUser);
    }
    public Optional<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}

