package com.phillip.ssoserver.repository.permissionrepo;

import com.phillip.ssoserver.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPermissionRepo extends JpaRepository<Permission, Long> {

    Permission findById(long id);

    @Query(value="select p from Permission p",nativeQuery = false)
    List<Permission> getAll();
}
