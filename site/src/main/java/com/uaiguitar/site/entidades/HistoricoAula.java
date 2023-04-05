package com.uaiguitar.site.entidades;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_historico")
public class HistoricoAula {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String cursoHistorico;

    private String aulaHistorico;

    public HistoricoAula(){}

    public HistoricoAula(UUID id, String cursoHistorico, String aulaHistorico) {
        this.id = id;
        this.cursoHistorico = cursoHistorico;
        this.aulaHistorico = aulaHistorico;
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
}
