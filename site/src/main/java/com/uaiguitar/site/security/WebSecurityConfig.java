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
            .authorizeHttpRequests()
            .requestMatchers("/login").permitAll()
            .requestMatchers(HttpMethod.GET, "/formulario").permitAll()
            // .requestMatchers(HttpMethod.POST, "/usuario").permitAll()
            .requestMatchers(HttpMethod.GET, "/usuario").hasAnyRole("GRATIS")
            .anyRequest().authenticated().and().cors().and()
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/process-login")
            .defaultSuccessUrl("/")
            .failureUrl("/login?error=true")
            .usernameParameter("username")
            .passwordParameter("senha")
            .permitAll()
            .and()
            .logout()
            .logoutSuccessUrl("/login?logout=true")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .permitAll().and().rememberMe().key("unicaESegura").tokenValiditySeconds(604800)
            .and().sessionManagement().sessionFixation().migrateSession().maximumSessions(2).and()
  
            .and().csrf().disable();

        return http.build();
    }
    
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
