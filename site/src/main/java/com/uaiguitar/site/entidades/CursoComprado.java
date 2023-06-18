package com.uaiguitar.site.entidades;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_curso_comprado")
public class CursoComprado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    @JoinTable(name = "tb_comprados_id_curso")
    private Curso cursoComprado;

    public CursoComprado(){}

    public CursoComprado(UUID id, Curso cursoComprado) {
        this.id = id;
        this.cursoComprado = cursoComprado;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Curso getCursoComprado() {
        return cursoComprado;
    }

    public void setCursoComprado(Curso cursoComprado) {
        this.cursoComprado = cursoComprado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CursoComprado that = (CursoComprado) o;
        return Objects.equals(id, that.id) && Objects.equals(cursoComprado, that.cursoComprado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cursoComprado);
    }
}
