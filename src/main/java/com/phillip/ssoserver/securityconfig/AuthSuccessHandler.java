package com.phillip.ssoserver.securityconfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
* 自定義驗證成功處理
*/

public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    ObjectMapper mapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth) throws IOException, ServletException {
        res.setStatus(HttpStatus.OK.value());
        res.setContentType("application/json");
        Map<String, Object> data = new HashMap<>();
        data.put("status","登入成功");
        data.put("使用者",auth.getPrincipal());
        res.getWriter().print(mapper.writeValueAsString(data));
    }
}
