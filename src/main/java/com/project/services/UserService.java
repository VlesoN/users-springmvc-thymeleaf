package com.project.services;

import com.project.models.User;
import com.project.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional
    public void delete(int id) {
        userDao.delete(id);
    }

    public Optional<User> findById(int id) {
        return userDao.findById(id);
    }

    @Transactional
    public void update(User updatedUser) {
        userDao.update(updatedUser);
    }
    public Optional<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}

