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
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/assets/**").permitAll()
                                .requestMatchers("/css/**").permitAll()
                                .requestMatchers("/img/**").permitAll()
                                .requestMatchers("/js/**").permitAll()
                )

                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/login").permitAll()
//                                .requestMatchers("/editar-aula").hasRole("ADMIN")
                                .requestMatchers("/curso/meus-cursos").hasRole("USER")
                                .requestMatchers("/usuario/conta").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/usuario/comprar").hasRole("USER")
                                .requestMatchers(HttpMethod.POST, "/usuario/criar").permitAll()
                                .requestMatchers("/criar-curso").hasRole("ADMIN")
                                .requestMatchers("/criar-modulo").hasRole("ADMIN")
                                .requestMatchers("/criar-aula").hasRole("ADMIN")
                                .requestMatchers("/editar-conteudo").hasRole("ADMIN")
                                .requestMatchers("/editar-modulo").hasRole("ADMIN")


                                .requestMatchers("/curso/todos").hasAnyRole("USER", "ADMIN")
//                                .requestMatchers("/minha-conta").hasAnyRole("UESR", "ADMIN")
//                                .requestMatchers("/menu").hasRole("GRATIS")
                                .requestMatchers("/aula/**").hasRole("ADMIN")
                                .requestMatchers("/modulo/**").hasRole("ADMIN")
                                .requestMatchers("/aula/adicionar").hasRole("ADMIN")
                                .requestMatchers("/curso/aula").hasRole("ADMIN")
                                .requestMatchers("/modulo/update").hasRole("ADMIN")



                                .requestMatchers("/curso/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/formulario").permitAll()
//                                .requestMatchers(HttpMethod.POST, "/usuario/historicoAula").permitAll()
//                                .requestMatchers(HttpMethod.GET, "/usuario/**").permitAll()
//                                .requestMatchers(HttpMethod.GET, "/usuario").hasAnyRole("GRATIS")
                                .anyRequest().authenticated()
                )

                .cors().and()

                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                .loginProcessingUrl("/process-login")
                                .defaultSuccessUrl("/usuario/conta", true)
                                .failureUrl("/login?error=true")
                                .usernameParameter("username")
                                .passwordParameter("senha")
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                )

                .rememberMe(me -> me
                                .key("unicaESegura")
                                .tokenValiditySeconds(604800))

                .sessionManagement(management -> management
                                .sessionFixation()
                                .migrateSession()
                                .maximumSessions(2))
                
                .csrf().disable();

        return http.build();
    }
    
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
