package com.uaiguitar.site.entidades;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_historico")
public class HistoricoAula {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nomeCurso;

    private String nomeAula;

    private String cursoHistorico;

    private String aulaHistorico;

    public HistoricoAula(){}

    public HistoricoAula(UUID id, String nomeCurso, String nomeAula, String cursoHistorico, String aulaHistorico) {
        this.id = id;
        this.nomeCurso = nomeCurso;
        this.nomeAula = nomeAula;
        this.cursoHistorico = cursoHistorico;
        this.aulaHistorico = aulaHistorico;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public String getNomeAula() {
        return nomeAula;
    }

    public void setNomeAula(String nomeAula) {
        this.nomeAula = nomeAula;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCursoHistorico() {
        return cursoHistorico;
    }

    public void setCursoHistorico(String cursoHistorico) {
        this.cursoHistorico = cursoHistorico;
    }

    public String getAulaHistorico() {
        return aulaHistorico;
    }

    public void setAulaHistorico(String aulaHistorico) {
        this.aulaHistorico = aulaHistorico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoricoAula that = (HistoricoAula) o;
        return Objects.equals(id, that.id) && Objects.equals(nomeCurso, that.nomeCurso) && Objects.equals(nomeAula, that.nomeAula) && Objects.equals(cursoHistorico, that.cursoHistorico) && Objects.equals(aulaHistorico, that.aulaHistorico);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomeCurso, nomeAula, cursoHistorico, aulaHistorico);
    }
}
