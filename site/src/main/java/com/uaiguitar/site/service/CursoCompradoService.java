package com.uaiguitar.site.service;

import com.uaiguitar.site.entidades.CursoComprado;
import com.uaiguitar.site.repository.CursoCompradoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CursoCompradoService {

    CursoCompradoRepository cursoCompradoRepository;

    public CursoCompradoService(CursoCompradoRepository cursoCompradoRepository) {
        this.cursoCompradoRepository = cursoCompradoRepository;
    }

    public List<CursoComprado> findAllCursoComprado (){
        return cursoCompradoRepository.findAll();
    }

    public CursoComprado createCursoComprado(CursoComprado cursoComprado){
        return cursoCompradoRepository.save(cursoComprado);
    }

    public void deleteCursoComprado(UUID id){
        cursoCompradoRepository.deleteById(id);
    }
}
