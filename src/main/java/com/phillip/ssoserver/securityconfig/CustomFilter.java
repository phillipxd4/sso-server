package com.phillip.ssoserver.securityconfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phillip.ssoserver.model.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public class CustomFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    ObjectMapper mapper;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        UsernamePasswordAuthenticationToken authRequest = null;
        LoginRequest loginRequest = null;
        String username = null;
        String password = null;

        if (!request.getContentType().contains("application/json") && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            try {
                InputStream is = request.getInputStream();
                loginRequest = mapper.readValue(is, LoginRequest.class);
                is.close();
                username = loginRequest.getUsername();
                password = loginRequest.getPassword();
                authRequest = new UsernamePasswordAuthenticationToken(username,password);
            } catch (IOException e) {
                e.printStackTrace();
                authRequest = new UsernamePasswordAuthenticationToken("","");
            }
            if (username == null) {
                username = "";
            }

            if (password == null) {
                password = "";
            }

            username = username.trim();

            this.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

}
