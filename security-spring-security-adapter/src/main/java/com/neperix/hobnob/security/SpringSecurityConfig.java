package com.neperix.hobnob.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class SpringSecurityConfig extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    private AuthenticatedUserRepository authenticatedUserRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new AuthenticationProvider() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {

                SpringAuthenticatedUser springAuthenticatedUser = authenticatedUserRepository
                        .findByUsername(authentication.getName())
                        .map(SpringAuthenticatedUser::of)
                        .orElseThrow(() -> new UsernameNotFoundException("Username " + authentication.getName() + " not found"));

                return new UsernamePasswordAuthenticationToken(
                        springAuthenticatedUser,
                        authentication.getCredentials(),
                        springAuthenticatedUser.getAuthorities());
            }

            @Override
            public boolean supports(Class<?> authentication) {
                return true;
            }
        });
    }
}
