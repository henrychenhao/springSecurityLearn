package com.henry.salary.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * @author chenhao
 */
@Configuration
public class MyResourceServerConfig extends ResourceServerConfigurerAdapter {

    public static final String RESOURCE_SALARY = "salary";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //资源ID
        resources.resourceId(RESOURCE_SALARY)
                //使用远程服务验证令牌的服务
                .tokenServices(tokenServices())
                //无状态模式
                .stateless(true);
    }

    /**
     * 配置安全策略
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        //校验请求
        http.authorizeRequests()
                // 路径匹配规则。
                .antMatchers("/salary/**")
                // 需要匹配scope
                .access("#oauth2.hasScope('all')")
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    /**
     * 配置access_token远程验证策略。
     * @return
     */
    public ResourceServerTokenServices tokenServices(){
//        DefaultTokenServices services = new DefaultTokenServices();
        RemoteTokenServices services = new RemoteTokenServices();
        services.setCheckTokenEndpointUrl("http://localhost:53020/uaa/oauth/check_token");
        services.setClientId("c1");
        services.setClientSecret("secret");
        return services;
    }
}