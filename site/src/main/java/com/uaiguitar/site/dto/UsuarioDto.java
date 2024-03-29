package com.uaiguitar.site.dto;

import java.util.Set;
import java.util.UUID;

import com.uaiguitar.site.entidades.Curso;
import com.uaiguitar.site.entidades.CursoComprado;
import com.uaiguitar.site.entidades.HistoricoAula;
import com.uaiguitar.site.entidades.Role;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;

public class UsuarioDto {
    
    private UUID id;
    private String username;
    private String nomeCompleto;
    private String email;
    private String senha;
    private Set<CursoComprado> cursosComprados;
    private Set<Role> roles;

    private Set<HistoricoAula> historicoAula;

    public UsuarioDto() {
    }

    public UsuarioDto(UUID id, String username, String nomeCompleto, String email, String senha,
            Set<CursoComprado> cursosComprados, Set<Role> roles, Set<HistoricoAula> historicoAula) {
        this.id = id;
        this.username = username;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.senha = senha;
        this.cursosComprados = cursosComprados;
        this.roles = roles;
        this.historicoAula = historicoAula;
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


    public Set<CursoComprado> getCursosComprados() {
        return cursosComprados;
    }


    public void setCursosComprados(Set<CursoComprado> cursosComprados) {
        this.cursosComprados = cursosComprados;
    }


    public Set<Role> getRoles() {
        return roles;
    }


    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<HistoricoAula> getHistoricoAula() {
        return historicoAula;
    }

    public void setHistoricoAula(Set<HistoricoAula> historicoAula) {
        this.historicoAula = historicoAula;
    }
}
