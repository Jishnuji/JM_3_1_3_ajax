package com.jm_preproject.spring_boot.spring_boot.service;

import com.jm_preproject.spring_boot.spring_boot.dao.UserDao;
import com.jm_preproject.spring_boot.spring_boot.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import com.jm_preproject.spring_boot.spring_boot.model.User;
import org.springframework.security.core.userdetails.*;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserDaoAndEncoder(UserDao userDao,
                                     PasswordEncoder passwordEncoder,
                                     RoleService roleService) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        return user;
    }

    @Override
    @Transactional
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public void save(User user) {
        String pass = passwordEncoder.encode(user.getPassword());
        user.setPassword(pass);
        userDao.save(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    @Transactional
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Override
    public Set<Role> getSetOfRolesFromList(List<String> rolesId) {
        Set<Role> listToRoleSet = new HashSet<>();
        for (String str : rolesId) {
            listToRoleSet.add(roleService.getRoleById(Long.parseLong(str)));
        }
        return listToRoleSet;
    }
}
