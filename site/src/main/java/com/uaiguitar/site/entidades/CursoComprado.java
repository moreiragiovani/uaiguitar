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

    private String cursoCompradoId;

    public CursoComprado(){}

    public CursoComprado(UUID id, String cursoCompradoId) {
        this.id = id;
        this.cursoCompradoId = cursoCompradoId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCursoCompradoId() {
        return cursoCompradoId;
    }

    public void setCursoCompradoId(String cursoCompradoId) {
        this.cursoCompradoId = cursoCompradoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CursoComprado that = (CursoComprado) o;
        return Objects.equals(id, that.id) && Objects.equals(cursoCompradoId, that.cursoCompradoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cursoCompradoId);
    }
}
