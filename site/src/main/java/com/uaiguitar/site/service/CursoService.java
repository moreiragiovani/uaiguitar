package com.uaiguitar.site.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

}
