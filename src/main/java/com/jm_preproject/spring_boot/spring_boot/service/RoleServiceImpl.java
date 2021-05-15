package com.jm_preproject.spring_boot.spring_boot.service;

import com.jm_preproject.spring_boot.spring_boot.dao.RoleDao;
import com.jm_preproject.spring_boot.spring_boot.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    @Transactional
    public List<Role> getRoleList() {
        return roleDao.getRoleList();
    }

    @Override
    @Transactional
    public Role getRoleById(Long id) {
        return roleDao.getRoleById(id);
    }
}
