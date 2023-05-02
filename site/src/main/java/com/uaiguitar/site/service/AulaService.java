package com.uaiguitar.site.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uaiguitar.site.entidades.Aula;
import com.uaiguitar.site.repository.AulaRepository;

@Service
public class AulaService {
    
    @Autowired
    AulaRepository aulaRepository;

    public List<Aula> findAllAula(){
        return aulaRepository.findAll();
    }

    public Aula findByIdAula(UUID id){
        Optional<Aula> aulaOpt = aulaRepository.findById(id);
        return aulaOpt.get();
    }

    public void createAula(Aula aula){
        aulaRepository.save(aula);
    }

    public void updateAula(UUID id, Aula a){
        Aula curso = aulaRepository.getReferenceById(id);
        updateAula(curso, a);
        aulaRepository.save(curso);
    }

    public void deleteAula(UUID id){
        aulaRepository.deleteById(id);
    }

    private void updateAula(Aula aula, Aula a) {
        aula.setNome(a.getNome());
        aula.setCompletoPdf(a.getCompletoPdf());
        aula.setDescricao(a.getDescricao());
        aula.setImgVideo(a.getImgVideo());
        aula.setVideo(a.getVideo());
    }

}
