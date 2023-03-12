package com.uaiguitar.site.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
        
        http
            .httpBasic()
            .and()
            .authorizeHttpRequests()
            .requestMatchers(HttpMethod.GET, "/login").permitAll()
            .requestMatchers(HttpMethod.GET, "/logout/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/h2").permitAll()
            .requestMatchers(HttpMethod.GET, "/usuario/**").hasAnyRole("USUARIO")
            .requestMatchers(HttpMethod.POST, "/usuario").hasAnyRole("USUARIO")
    //                .antMatchers(HttpMethod.POST, "/parking-spot").hasRole("USER")
    //                .antMatchers(HttpMethod.DELETE, "/parking-spot/**").hasRole("ADMIN")
            .anyRequest().authenticated()
            .and()
            .csrf().disable();

        return http.build();
    }
    
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}