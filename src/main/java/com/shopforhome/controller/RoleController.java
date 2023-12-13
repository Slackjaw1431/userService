package com.shopforhome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shopforhome.entity.Role;
import com.shopforhome.service.RoleServiceImp;

@CrossOrigin()
@RestController
public class RoleController {

    @Autowired
    private RoleServiceImp roleService;

    @PostMapping({"/createNewRole"})
    public Role createNewRole(@RequestBody Role role) {
        return roleService.createNewRole(role);
    }
}
