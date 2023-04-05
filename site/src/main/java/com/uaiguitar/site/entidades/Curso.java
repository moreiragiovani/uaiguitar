package com.uaiguitar.site.entidades;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_curso")
public class Curso implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String nome;
    private String descricao;
    private String imagem;
    private String videoApresentacao;

    @OneToMany
    @JoinTable(name = "tb_curso_modulos")
    private List<Modulo> modulo;

    public Curso(){}

    public Curso(UUID id, String nome, String descricao, String imagem, String videoApresentacao, List<Modulo> modulo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.imagem = imagem;
        this.videoApresentacao = videoApresentacao;
        this.modulo = modulo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getVideoApresentacao() {
        return videoApresentacao;
    }

    public void setVideoApresentacao(String videoApresentacao) {
        this.videoApresentacao = videoApresentacao;
    }

    public List<Modulo> getModulo() {
        return modulo;
    }

    public void setModulo(List<Modulo> modulo) {
        this.modulo = modulo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
        Curso other = (Curso) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }  
}
