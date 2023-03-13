package com.uaiguitar.site.dto;

import java.util.Set;
import java.util.UUID;

import com.uaiguitar.site.entidades.Curso;
import com.uaiguitar.site.entidades.Role;

public class UsuarioDto {
    
    private UUID id;
    private String username;
    private String nomeCompleto;
    private String email;
    private String senha;
    private Set<Curso> cursosComprados;
    private Set<Role> roles;

    
    
    public UsuarioDto() {
    }

    public UsuarioDto(UUID id, String username, String nomeCompleto, String email, String senha,
            Set<Curso> cursosComprados, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.senha = senha;
        this.cursosComprados = cursosComprados;
        this.roles = roles;
    }

    public UUID getId() {
        return id;
    }


    public void setId(UUID id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getNomeCompleto() {
        return nomeCompleto;
    }


    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getSenha() {
        return senha;
    }


    public void setSenha(String senha) {
        this.senha = senha;
    }


    public Set<Curso> getCursosComprados() {
        return cursosComprados;
    }


    public void setCursosComprados(Set<Curso> cursosComprados) {
        this.cursosComprados = cursosComprados;
    }


    public Set<Role> getRoles() {
        return roles;
    }


    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }   
}
