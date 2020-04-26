package com.phillip.ssoserver.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new CustomFilter(), UsernamePasswordAuthenticationFilter.class);
        http.formLogin()
                .and()
                .httpBasic();
        http.requestMatchers().antMatchers("/**");
        http.authorizeRequests().antMatchers("/login","/oauth/**","/gettoken","/hello").permitAll()
                .anyRequest().authenticated();
        //关闭跨站请求防护
        http.cors().and().csrf().disable();
        // 前后端分离是无状态的，所以暫時不用session，將登陆信息保存在token中。
//      http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider);
    }

    @Autowired
    CustomAuthenticationProvider provider;
}
