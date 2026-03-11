package com.service.services.Configuration;

import com.service.services.SpringSecurity.JwtVerify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    JwtVerify jwtVerify;

    private static final String[] publicRoutes = {
            "/auth/**","/otp/**"
    };

    @Bean
    public SecurityFilterChain configureFilter(HttpSecurity httpSecurity){
    httpSecurity.csrf((csrf)->{
        csrf.disable();
    }).sessionManagement((session)->{
        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }).authorizeHttpRequests((request)->{
        request.requestMatchers(publicRoutes).permitAll()
                .anyRequest().authenticated();

    }).addFilterBefore(jwtVerify, UsernamePasswordAuthenticationFilter.class);
     return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager getManager(AuthenticationConfiguration configuration){
     return configuration.getAuthenticationManager();
    }

}
