package com.phillip.ssoserver.securityconfig;

import com.phillip.ssoserver.entity.Permission;
import com.phillip.ssoserver.entity.Role;
import com.phillip.ssoserver.entity.User;
import com.phillip.ssoserver.repository.userrepo.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/*
* 自定義帳號密碼驗證
*/

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    IUserRepo userRepo;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = bCryptPasswordEncoder.encode(authentication.getCredentials().toString());
        Optional<User> user = userRepo.findByAccountAndPassword(name, password);
        Set<SimpleGrantedAuthority> permissionList = null;
        Set<String> tempPermissionList = new HashSet<>();
        if(user.isPresent()){
            List<Role> roleList = user.get().getRoleList();

            //一個user多角色 一個角色多權限
            for(Role role : roleList){
                Set<String> collect = role.getPermissionList().stream().map(p -> p.getPermissionName()).collect(Collectors.toSet());
                tempPermissionList.addAll(collect);
            }

            permissionList = tempPermissionList.stream().map(str -> new SimpleGrantedAuthority(str)).collect(Collectors.toSet());
        }

        return new UsernamePasswordAuthenticationToken(user.get(), null, permissionList);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(
                UsernamePasswordAuthenticationToken.class);
    }
}
