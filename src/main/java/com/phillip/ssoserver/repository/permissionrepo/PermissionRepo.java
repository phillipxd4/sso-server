package com.phillip.ssoserver.repository.permissionrepo;

import com.phillip.ssoserver.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRepo extends JpaRepository<Permission, Long> {

    Permission findById(long id);
    List<Permission> getAll();
}
