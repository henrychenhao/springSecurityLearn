package com.henry.springbootsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 注入一个自定义的配置
 * @author chenhao
 */
@Configuration
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    /**
     * 配置安全拦截策略
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //链式配置拦截策略
        //关闭csrf跨域检查
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/index.html","/css/**","/img/**","/js/**").permitAll()
                //配置资源权限
                .antMatchers("/mobile/**").hasAuthority("mobile")
                .antMatchers("/salary/**").hasAuthority("salary")
                //common下的请求直接通过
                .antMatchers("/common/**").permitAll()
                //其他请求需要登录
                .anyRequest().authenticated()
                .and() //并行条件
                .rememberMe().userDetailsService(userDetailsService)
                .and()
                //可从默认的login页面登录，并且登录后跳转到main.html
                .formLogin()
                .loginPage("/index.html").loginProcessingUrl("/login")
                .defaultSuccessUrl("/main.html")
                .failureUrl("/common/loginFailed");
    }
}