package com.project.controllers;

import com.project.models.User;
import com.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/users")
public class HibernateUserController {

    private final UserService userService;

    @Autowired
    public HibernateUserController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "list";
    }

    @GetMapping("/add")
    public String getAddPage(Model model) {
        model.addAttribute("user", new User());
        return "add";
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
        return "edit";
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
        return "delete";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
