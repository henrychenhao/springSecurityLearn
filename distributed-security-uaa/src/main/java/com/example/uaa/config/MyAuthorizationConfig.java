package com.example.uaa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * @author chenhao
 */
@Configuration
public class MyAuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenStore tokenStore;
    /**
     * 会通过之前的ClientDetailsServiceConfigurer注入到Spring容器中
     */
    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private JwtAccessTokenConverter accessTokenConvert;

    public AuthorizationServerTokenServices tokenService() {
        DefaultTokenServices service = new DefaultTokenServices();
        //客户端详情服务
        service.setClientDetailsService(clientDetailsService);
        //允许令牌自动刷新
        service.setSupportRefreshToken(true);
        //令牌存储策略-内存
        service.setTokenStore(tokenStore);
        service.setTokenEnhancer(accessTokenConvert);
        // 令牌默认有效期2小时
        service.setAccessTokenValiditySeconds(7200);
        // 刷新令牌默认有效期3天
        service.setRefreshTokenValiditySeconds(259200);
        return service;
    }

    /**
     * 3.配置令牌端点的安全约束
     * 用来配置令牌端点的安全约束. ClientDetails
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                // oauth/token_key公开
                .tokenKeyAccess("permitAll()")
                // oauth/check_token公开
                .checkTokenAccess("permitAll()")
                // 表单认证，申请令牌
                .allowFormAuthenticationForClients();
    }

    /**
     * 1.配置令牌客户端
     * 用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，
     * 你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //内存配置的方式配置用户信息
        clients.inMemory()//内存方式
                //client_id
                .withClient("c1")
                //客户端秘钥
                .secret(new BCryptPasswordEncoder().encode("secret"))
                //客户端拥有的资源列表
                .resourceIds("salary")
                //该client允许的授权类型
                .authorizedGrantTypes("authorization_code",
                        "password", "client_credentials", "implicit", "refresh_token")
                //允许的授权范围
                .scopes("all")
                //跳转到授权页面
                .autoApprove(false)
                //回调地址
                .redirectUris("http://www.baidu.com");
//                .and() //继续注册其他客户端
//                .withClient()
//                ...
//   加载自定义的客户端管理服务 //   clients.withClientDetails(clientDetailsService);
    }

    /**
     * 2.配置令牌服务
     * 用来配置令牌（token）的访问端点和令牌服务(tokenservices)。
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
//                .pathMapping("/oauth/confirm_access","/customer/confirm_access")//定制授权同意页面
                //认证管理器
                .authenticationManager(authenticationManager)
                //密码模式的用户信息管理
                .userDetailsService(userDetailsService)
                //授权码服务
                .authorizationCodeServices(authorizationCodeServices)
                //令牌管理服务
                .tokenServices(tokenService())
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }

    /**
     * 设置授权码模式的授权码如何存取，暂时用内存方式。
     * @return
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices(){
        return new InMemoryAuthorizationCodeServices();
        //JdbcAuthorizationCodeServices
    }
}