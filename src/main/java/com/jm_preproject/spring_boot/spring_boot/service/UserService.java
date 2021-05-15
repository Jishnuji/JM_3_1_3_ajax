package com.jm_preproject.spring_boot.spring_boot.service;

import com.jm_preproject.spring_boot.spring_boot.model.Role;
import com.jm_preproject.spring_boot.spring_boot.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {
    List<User> getUsers();
    User getUserById(Long id);
    void save(User user);
    void update(User user);
    void delete(Long id);
    User getUserByName(String name);
    Set<Role> getSetOfRolesFromList(List<String> rolesId);
}
