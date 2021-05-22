package com.jm_preproject.spring_boot.spring_boot.controller;

import com.jm_preproject.spring_boot.spring_boot.model.Role;
import com.jm_preproject.spring_boot.spring_boot.model.User;
import com.jm_preproject.spring_boot.spring_boot.service.RoleService;
import com.jm_preproject.spring_boot.spring_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public String index(Model model, Principal principal) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("roles", roleService.getRoleList());
        model.addAttribute("log_user", userService.getUserByName(principal.getName()));
        return "index";
    }

    @GetMapping("/edit/{id}")
    @ResponseBody
    public User getUser (@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public User deleteUser (@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PatchMapping ("/update")
    public String updateUser(@Validated(User.class) @ModelAttribute("user") User user,
                             @RequestParam("authorities") List <String> listId) {
        Set<Role> lr = userService.getSetOfRolesFromList(listId);
        user.setRoles(lr);
        userService.update(user);
        return "redirect:/admin";
    }

    @PostMapping("/new_user")
    public String createUser(@ModelAttribute("user") User user,
                         @RequestParam("authorities") List <String> listId) {
        Set<Role> lr = userService.getSetOfRolesFromList(listId);
        user.setRoles(lr);
        userService.save(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/remove")
    public String removeUser(@ModelAttribute("user") User user) {
        userService.delete(user.getId());
        return "redirect:/admin";
    }
}
