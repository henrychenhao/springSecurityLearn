package com.example.uaa.config;

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
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 密码管理器
     * @return
     */
    @Bean
    public PasswordEncoder getPassWordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    /**
     * 从父类加载认证管理器
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager userDetailsManager =
                new InMemoryUserDetailsManager(User.withUsername("admin").password(getPassWordEncoder().encode("admin")).authorities("mobile","salary").build()
                ,User.withUsername("manager").password(getPassWordEncoder().encode("manager")).authorities("salary").build()
                ,User.withUsername("worker").password(getPassWordEncoder().encode("worker")).authorities("worker").build());
        return userDetailsManager;
    }

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
                .anyRequest().authenticated() //其他请求需要登录
                .and() //并行条件
                .formLogin(); //可从默认的login页面登录，并且登录后跳转到main.html
    }
}
