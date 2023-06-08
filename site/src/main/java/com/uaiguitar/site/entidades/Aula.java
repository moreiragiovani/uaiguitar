package com.uaiguitar.site.entidades;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_aula")
public class Aula implements Serializable, Comparable<Aula>{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private Integer indiceDaAula;
    private String nome;
    private String descricao;
    private String completoPdf;
    private String imgVideo;
    private String video;

    private UUID moduloId;

    @ManyToOne
    @JoinTable(name = "tb_aula_curso")
    private Curso curso;
    
    public Aula() {
    }

    public Aula(UUID id,Integer indice, String nome, String descricao, String completoPdf, String imgVideo, String video, UUID moduloId, Curso curso) {
        this.id = id;
        this.indiceDaAula = indice;
        this.nome = nome;
        this.descricao = descricao;
        this.completoPdf = completoPdf;
        this.imgVideo = imgVideo;
        this.video = video;
        this.moduloId = moduloId;
        this.curso = curso;
    }
    @JsonIgnore
    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
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

    public String getCompletoPdf() {
        return completoPdf;
    }

    public void setCompletoPdf(String completoPdf) {
        this.completoPdf = completoPdf;
    }

    public String getImgVideo() {
        return imgVideo;
    }

    public void setImgVideo(String imgVideo) {
        this.imgVideo = imgVideo;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public UUID getModuloId() {
        return moduloId;
    }

    public void setModuloId(UUID moduloId) {
        this.moduloId = moduloId;
    }

    public Integer getIndiceDaAula() {
        return indiceDaAula;
    }

    public void setIndiceDaAula(Integer indiceDaAula) {
        this.indiceDaAula = indiceDaAula;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((indiceDaAula == null) ? 0 : indiceDaAula.hashCode());
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
        Aula other = (Aula) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (indiceDaAula == null) {
            if (other.indiceDaAula != null)
                return false;
        } else if (!indiceDaAula.equals(other.indiceDaAula))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }


    @Override
    public int compareTo(Aula aulas) {
        if(this.indiceDaAula < aulas.getIndiceDaAula()){
            return -1;
        }else{
            return 1;
        }
    }
}
