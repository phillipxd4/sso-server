package com.phillip.ssoserver.repository.rolerepo;

import com.phillip.ssoserver.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IRoleRepo extends JpaRepository<Role, Long> {

    Role findById(long id);

    @Query(value="select r from Role r",nativeQuery = false)
    List<Role> getAll();
}
