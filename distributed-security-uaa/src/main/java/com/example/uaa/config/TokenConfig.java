package com.example.uaa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author hao.chen
 * @date 2021/9/23 2:46 下午
 */
@Configuration
public class TokenConfig {
    private static final String SIGN_KEY="henry_uaa";
    @Bean
    public TokenStore tokenStore() {
        //使用基于内存的普通令牌
        //return new InMemoryTokenStore();
        return new JwtTokenStore(accessTokenConvert());
    }
    @Bean
    public JwtAccessTokenConverter accessTokenConvert(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(SIGN_KEY);
        return converter;
    }
}