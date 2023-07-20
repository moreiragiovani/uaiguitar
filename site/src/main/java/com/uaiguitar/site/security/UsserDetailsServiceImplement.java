package com.uaiguitar.site.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.uaiguitar.site.entidades.UsuarioDetails;
import com.uaiguitar.site.service.UsuarioService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsserDetailsServiceImplement implements UserDetailsService{

    @Autowired
    UsuarioService service;

    public UsserDetailsServiceImplement(UsuarioService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{

            UsuarioDetails usuario = service.findByUsername(username);
            User user = new User(usuario.getUsername(), usuario.getSenha(), 
            true, true, true,
            true, usuario.getAuthorities());
    
            return user;
        }catch (InternalAuthenticationServiceException e){
            System.out.printf("Usuario não encontrado " + e.getMessage());
            return null;
        }
        catch(Exception e){
            System.out.printf("Usuario não encontrado " + e.getMessage());
            return null;
        }
        
    }
    
}
