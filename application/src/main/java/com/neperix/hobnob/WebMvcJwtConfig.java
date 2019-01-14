package com.neperix.hobnob;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.neperix.hobnob.security.jwt.JwtAuthenticationFilter;

@Configuration
public class WebMvcJwtConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                    .antMatchers("/swagger-ui.html").permitAll()
                    .antMatchers("/auth").permitAll()
                    .antMatchers("/refresh").permitAll()
                    .anyRequest().authenticated();

        httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.headers().cacheControl();
    }

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
}
