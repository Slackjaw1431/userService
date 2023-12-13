package com.shopforhome.service;

import com.shopforhome.entity.Role;
import com.shopforhome.repo.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp {

    @Autowired
    private RoleRepository roleDao;

    public Role createNewRole(Role role) {
        return roleDao.save(role);
    }
}
