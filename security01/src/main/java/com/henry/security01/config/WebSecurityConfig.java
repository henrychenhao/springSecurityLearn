package com.henry.security01.config;

import com.henry.security01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author hao.chen
 * @date 2021/9/14 5:10 下午
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //表单提交
        http.formLogin()
                //自定义登录页面
                .loginPage("/login.html")
                //登录访问路径，必须和表单提交接口一样
                .loginProcessingUrl("/user/login")
                .failureForwardUrl("/toerror")
                .successForwardUrl("/main")
                //认证成功之后跳转的路径
                .defaultSuccessUrl("/admin/index")
                .and().authorizeRequests()
                .antMatchers("/user/login","/login.html","/error.html").permitAll()
                .anyRequest().authenticated()//需要认证
                .and().csrf().disable();//关闭csrf防护
    }
}
