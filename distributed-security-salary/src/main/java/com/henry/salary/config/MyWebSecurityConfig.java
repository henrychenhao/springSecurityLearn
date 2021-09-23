package com.henry.salary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author hao.chen
 * @date 2021/9/23 2:46 下午
 */
@Configuration
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 配置用户的安全拦截策略
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //链式配置拦截策略
        http.csrf().disable()//关闭csrf跨域检查
                .authorizeRequests()
                .antMatchers("/salary/**")
                //.hasAuthority("salary")
                .authenticated()
                .anyRequest().permitAll();
    }
}
