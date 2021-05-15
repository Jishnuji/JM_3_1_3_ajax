package com.jm_preproject.spring_boot.spring_boot.controller;

import com.jm_preproject.spring_boot.spring_boot.model.Role;
import com.jm_preproject.spring_boot.spring_boot.model.User;
import com.jm_preproject.spring_boot.spring_boot.service.RoleService;
import com.jm_preproject.spring_boot.spring_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public String index(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "index";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getRoleList());
        return "update";
    }

    @PatchMapping ("/{id}")
    public String updateUser(@Validated(User.class) @ModelAttribute("user") User user,
                             @RequestParam("authorities") List <String> listId,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "error";
        }
        Set<Role> lr = userService.getSetOfRolesFromList(listId);
        user.setRoles(lr);
        userService.update(user);
        return "redirect:/admin";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        List<Role> lr = roleService.getRoleList();
        System.out.println(lr);
        model.addAttribute(new User());
        model.addAttribute("roles", roleService.getRoleList());
        return "new";
    }

    @PostMapping()
    public String create(@Validated(User.class) @ModelAttribute("user") User user,
                         @RequestParam("authorities") List <String> listId,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "error";
        }
        Set<Role> lr = userService.getSetOfRolesFromList(listId);
        user.setRoles(lr);
        userService.save(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
