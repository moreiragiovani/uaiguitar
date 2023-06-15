package com.uaiguitar.site.entidades;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_usuario")
public class Usuario implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    @NotBlank(message = "Digite um nome de usuario")
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Digite um nome de usuario")
    private String nomeCompleto;

    @Email(message = "Digite um email válido.")
    private String email;

    @NotBlank(message = "Você precisa criar uma senha.")
    private String senha;

    @OneToMany
    @JoinTable(name = "tb_cursos_comprados")
    private Set<Curso> cursosComprados;

    @ManyToMany
    @JoinTable(name = "tb_usuario_role",
        joinColumns = @JoinColumn(name = "id_usuario"),
        inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<Role> roles;



    public Usuario() {
    }

    public Usuario(UUID id, String username, String nomeCompleto, String email, String senha,
                   Set<Curso> cursosComprados,
                   Set<Role> roles) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(username, usuario.username) && Objects.equals(nomeCompleto, usuario.nomeCompleto) && Objects.equals(email, usuario.email) && Objects.equals(senha, usuario.senha) && Objects.equals(cursosComprados, usuario.cursosComprados) && Objects.equals(roles, usuario.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, nomeCompleto, email, senha, cursosComprados, roles);
    }
}
