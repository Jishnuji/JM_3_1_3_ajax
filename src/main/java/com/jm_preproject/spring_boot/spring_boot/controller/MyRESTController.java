package com.jm_preproject.spring_boot.spring_boot.controller;

import com.jm_preproject.spring_boot.spring_boot.model.User;
import com.jm_preproject.spring_boot.spring_boot.service.RoleService;
import com.jm_preproject.spring_boot.spring_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public MyRESTController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        userService.save(user);
        User userWithEmail = userService.getUserByEmail(user.getEmail());
        return userService.getUserById(userWithEmail.getId());
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        userService.update(user);
        return userService.getUserById(user.getId());
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "User with ID = " + id + " was deleted";
    }
}
