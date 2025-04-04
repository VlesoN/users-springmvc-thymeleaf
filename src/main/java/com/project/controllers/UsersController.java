package com.project.controllers;

import com.project.repository.SpringMvcUserRepository;
import com.project.models.User;
import com.project.repository.UserRepository;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users/list";
    }

    @GetMapping("/add")
    public String getAddPage(Model model) {
        model.addAttribute("user", new User());
        return "users/add";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String getEditPage(@PathVariable("id") int id,
                              Model model) {
        userService.getUserById(id)
                .ifPresent(user ->
                        model.addAttribute("user", user));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String editUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/delete")
    public String getDeletePage(@PathVariable("id") int id,
                                Model model) {
        userService.getUserById(id)
                .ifPresent(user ->
                        model.addAttribute("user", user));
        return "users/delete";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
