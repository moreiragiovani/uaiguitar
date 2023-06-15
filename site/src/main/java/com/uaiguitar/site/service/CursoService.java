package com.uaiguitar.site.service;

import java.util.*;

import com.uaiguitar.site.entidades.HistoricoAula;
import com.uaiguitar.site.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uaiguitar.site.entidades.Curso;
import com.uaiguitar.site.repository.CursoRepository;

@Service
public class CursoService {
    
    @Autowired
    CursoRepository cursoRepository;

    public List<Curso> findAllCursos(){
        return cursoRepository.findAll();
    }

    public Curso findBynome(String nome){
        return cursoRepository.findBynome(nome).get();
    }

    public Curso findByIdCurso(UUID id){
        Optional<Curso> userOpt = cursoRepository.findById(id);
        return userOpt.get();
    }

    public void createCurso(Curso user){
        cursoRepository.save(user);
    }

    public void updateCurso(UUID id, Curso c){
        Curso curso = cursoRepository.getReferenceById(id);
        updateUser(curso, c);
        cursoRepository.save(curso);
    }

    public void deleteCurso(UUID id){
        cursoRepository.deleteById(id);
    }

    private void updateUser(Curso curso, Curso c) {
        curso.setNome(c.getNome());
        curso.setDescricao(c.getDescricao());
        curso.setImagem(c.getImagem());
        curso.setModulo(c.getModulo());
        curso.setVideoApresentacao(c.getVideoApresentacao());
    }

    public void historicoAulaAtualizado(HistoricoAula historicoAula){
        Curso curso = cursoRepository.findById(UUID.fromString(
                historicoAula.getCursoHistorico())).get();
//        if(!curso.getHistoricoAula().getCursoHistorico().equals(null)) {
//            if(curso.getHistoricoAula().getCursoHistorico().equals(historicoAula.getCursoHistorico())){
//            }
//        }
        curso.setHistoricoAula(historicoAula);
        cursoRepository.save(curso);
    }

}
