package com.phillip.ssoserver.repository.userrepo;

import com.phillip.ssoserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepo extends JpaRepository<User,Long> {

    User findByAccount(String account);
    User findById(long id);

    @Query(value="select u from User u where u.account=:account and u.password=:password",nativeQuery = false)
    User findByAccountAndPassword(@Param("account") String account, @Param("password") String password);

    @Query(value="select u from User u",nativeQuery = false)
    List<User> getAll();

}
