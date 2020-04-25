package com.phillip.ssoserver.repository.userrepo;

import com.phillip.ssoserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRepo extends JpaRepository<User,Long> {

    User findByAccount(String account);
    User findById(long id);
    List<User> getAll();

}
