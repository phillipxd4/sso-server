package com.phillip.ssoserver.repository.userrepo;

import com.phillip.ssoserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepo extends JpaRepository<User,Long> {

    User findByAccount(String account);
    User findById(long id);
    Optional<User> findByAccountAndPassword(String account, String password);
    @Query(value="select u from User u",nativeQuery = false)
    List<User> getAll();

}
