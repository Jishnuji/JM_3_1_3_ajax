package com.jm_preproject.spring_boot.spring_boot.controller;

import com.jm_preproject.spring_boot.spring_boot.service.RoleService;
import com.jm_preproject.spring_boot.spring_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;


@Controller
@RequestMapping("/")
public class UsersController {
    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "user")
    public String index(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByName(principal.getName()));
        return "user";
    }
}