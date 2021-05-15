package com.jm_preproject.spring_boot.spring_boot.service;
import com.jm_preproject.spring_boot.spring_boot.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRoleList();
    Role getRoleById(Long id);
}
