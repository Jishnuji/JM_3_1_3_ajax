package com.jm_preproject.spring_boot.spring_boot.dao;


import com.jm_preproject.spring_boot.spring_boot.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();
    User getUserById(Long id);
    void save(User user);
    void update(User user);
    void delete(Long id);
    User getUserByName(String name);
}
