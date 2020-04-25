package com.phillip.ssoserver.controller;

import com.phillip.ssoserver.entity.Permission;
import com.phillip.ssoserver.entity.Role;
import com.phillip.ssoserver.entity.User;
import com.phillip.ssoserver.repository.permissionrepo.IPermissionRepo;
import com.phillip.ssoserver.repository.rolerepo.IRoleRepo;
import com.phillip.ssoserver.repository.userrepo.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    IUserRepo userRepo;

    @Autowired
    IRoleRepo roleRepo;

    @Autowired
    IPermissionRepo perRepo;

    @GetMapping("allUser")
    public List<User> getAllUser(){
        return userRepo.getAll();
    }

    @GetMapping("allRole")
    public List<Role> getAllRole(){
        return roleRepo.getAll();
    }

    @GetMapping("allPer")
    public List<Permission> getAllPer(){
        return perRepo.getAll();
    }
}
