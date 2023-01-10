package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

@Controller
public class AdminController {

    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    RoleServiceImpl roleServiceImpl;

    @GetMapping("/admin")
    public String showUserListForAdmin(Model model) {
        model.addAttribute("users", userServiceImpl.findAll());
        return "admin";
    }

    @GetMapping("/admin/add")
    public String showSignUpForm(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", roleServiceImpl.showRoles());
        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(User user) {
        userServiceImpl.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userServiceImpl.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userServiceImpl.findById(id);
        model.addAttribute("roles", roleServiceImpl.showRoles());
        model.addAttribute("user" , user);
        return "update-user";
    }

    @PostMapping("/admin/update/{id}")
    public String updateUser(@PathVariable("id") long id, User user) {
        userServiceImpl.update(id, user);
        return "redirect:/admin";
    }

}
