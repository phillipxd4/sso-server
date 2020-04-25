package com.phillip.ssoserver.repository.rolerepo;

import com.phillip.ssoserver.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepo extends JpaRepository<Role, Long> {

    Role findById(long id);
    List<Role> getAll();
}
