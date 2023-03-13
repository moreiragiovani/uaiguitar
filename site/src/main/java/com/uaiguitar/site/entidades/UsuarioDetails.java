package com.uaiguitar.site.entidades;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioDetails implements UserDetails {

    private UUID id;
    private String username;
    private String nomeCompleto;
    private String email;
    private String senha;
    private Set<Curso> cursosComprados;
    private Set<Role> roles;
  
    public UsuarioDetails() {
    }

    public UsuarioDetails(Usuario usuario) {
        this.id = usuario.getId();
        this.username = usuario.getUsername();
        this.nomeCompleto = usuario.getNomeCompleto();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.cursosComprados = usuario.getCursosComprados();
        this.roles = usuario.getRoles();
    }
  
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    
    public Set<Curso> getCursosComprados() {
        return cursosComprados;
    }

    public void setCursosComprados(Set<Curso> cursosComprados) {
        this.cursosComprados = cursosComprados;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UsuarioDetails other = (UsuarioDetails) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

// METODOS DO USER DETAILS 

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
       
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.username;
        
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    
}
