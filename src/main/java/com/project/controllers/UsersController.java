package com.project.controllers;

import com.project.dao.UserDao;
import com.project.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserDao userDao;

    @Autowired
    public UsersController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("users", userDao.getAllUsers());
        return "users/list";
    }

    @GetMapping("/add")
    public String getAddPage(Model model) {
        model.addAttribute("user", new User());
        return "users/add";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") User user) {
        userDao.add(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String getEditPage(@PathVariable("id") int id,
                              Model model) {
        userDao.getUserById(id)
                .ifPresent(user ->
                    model.addAttribute("user", user));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String editUser(@PathVariable("id") int id,
                           @ModelAttribute("user") User user) {
        userDao.update(id, user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/delete")
    public String getDeletePage(@PathVariable("id") int id,
                                Model model) {
        userDao.getUserById(id)
                        .ifPresent(user ->
                        model.addAttribute("user",user));
        return "users/delete";
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id,
                             @ModelAttribute("user") User user) {
        userDao.delete(id,user);
        return "redirect:/users";
    }
}
