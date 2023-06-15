package com.uaiguitar.site.entidades;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_modulo")
public class Modulo implements Serializable, Comparable<Modulo>{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private Integer indiceModulo;
    private String nome;
    private String descricao;
    private UUID cursoId;
    @OneToMany
    @JoinTable(name = "tb_modulo_aula")
    private Set<Aula> aulas;

    public Modulo() {
    }

    public Modulo(UUID id,Integer indice, String nome, String descricao, UUID cursoId, Set<Aula> aulas) {
        this.id = id;
        this.indiceModulo = indice;
        this.nome = nome;
        this.descricao = descricao;
        this.cursoId = cursoId;
        this.aulas = aulas;
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

    public UUID getCursoId() {
        return cursoId;
    }

    public void setCursoId(UUID cursoId) {
        this.cursoId = cursoId;
    }

    public Set<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(Set<Aula> aulas) {
        this.aulas = aulas;
    }

    public Integer getIndiceModulo() {
        return indiceModulo;
    }

    public void setIndiceModulo(Integer indiceModulo) {
        this.indiceModulo = indiceModulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Modulo modulo)) return false;
        return Objects.equals(getId(), modulo.getId()) && Objects.equals(getIndiceModulo(), modulo.getIndiceModulo()) && Objects.equals(getNome(), modulo.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIndiceModulo(), getNome());
    }


    @Override
    public int compareTo(Modulo modulo) {
        if(this.indiceModulo < modulo.getIndiceModulo()){
            return -1;
        }else{
            return 1;
        }
    }
}
