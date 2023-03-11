package com.uaiguitar.site.dto;

import java.util.UUID;

import com.uaiguitar.site.entidades.Usuario;

public class UsuarioDto {
    
    private UUID id;
    private String uername;
    private String nomeCompleto;
    private String email;
    private String senha;
    
    public UsuarioDto() {
    }

    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.uername = usuario.getUername();
        this.nomeCompleto = usuario.getNomeCompleto();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUername() {
        return uername;
    }

    public void setUername(String uername) {
        this.uername = uername;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((uername == null) ? 0 : uername.hashCode());
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
        UsuarioDto other = (UsuarioDto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (uername == null) {
            if (other.uername != null)
                return false;
        } else if (!uername.equals(other.uername))
            return false;
        return true;
    }

    
}
