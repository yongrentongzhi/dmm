package com.dmm.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    public DmmUserDetailServiceImpl userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenStore redisTokenStore;

    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
        security.checkTokenAccess("isAuthenticated()");
        security.tokenKeyAccess("isAuthenticated()");

    }

    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("order-client")
                .secret(passwordEncoder.encode("order-secret-8888"))
                .authorizedGrantTypes("refresh_token", "authorization_code", "password")
                .accessTokenValiditySeconds(3600)
                .scopes("all")
                .and()
                .withClient("user-client")
                .secret(passwordEncoder.encode("user-secret-8888"))
                .authorizedGrantTypes("refresh_token", "authorization_code", "password")
                .accessTokenValiditySeconds(3600)
                .scopes("all");
    }

    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        /**
         * redis token 方式
         */
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenStore(redisTokenStore);

    }


}
