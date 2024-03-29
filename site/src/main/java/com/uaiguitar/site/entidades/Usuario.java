package com.uaiguitar.site.entidades;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Table(name = "tb_usuario")
public class Usuario implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    @NotEmpty(message = "Digite um nome de usuario")
    @Column(nullable = false, unique = true)
    private String username;

    @NotEmpty(message = "Nome vazio")
    private String nomeCompleto;

    @NotEmpty(message = "Email obrigatório")
    @Email(message = "Digite um email válido.")
    private String email;

    @NotEmpty(message = "Senha obrigatória.")
    private String senha;

    @OneToMany
    @JoinTable(name = "tb_cursos_comprados")
    private Set<CursoComprado> cursosComprados;

    @ManyToMany
    @JoinTable(name = "tb_usuario_role",
        joinColumns = @JoinColumn(name = "id_usuario"),
        inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<Role> roles;

    @OneToMany
    @JoinTable(name = "tb_usuario_historico_aula")
    private Set<HistoricoAula> historicoAula;

    public Usuario() {
    }

    public Usuario(UUID id, String username, String nomeCompleto, String email, String senha,
                   Set<CursoComprado> cursosComprados,
                   Set<Role> roles, Set<HistoricoAula> historicoAula) {
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
