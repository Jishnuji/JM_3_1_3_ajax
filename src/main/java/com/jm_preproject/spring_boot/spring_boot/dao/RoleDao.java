package com.jm_preproject.spring_boot.spring_boot.dao;



import com.jm_preproject.spring_boot.spring_boot.model.Role;

import java.util.List;

public interface RoleDao {
    List<Role> getRoleList();
    Role getRoleById(Long id);
}
