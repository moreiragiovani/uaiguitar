package com.uaiguitar.site.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uaiguitar.site.entidades.Modulo;
import com.uaiguitar.site.repository.ModuloRepository;

@Service
public class ModuloService {
    
    @Autowired
    ModuloRepository moduloRepository;

    public List<Modulo> findAllCursos(){
        return moduloRepository.findAll();
    }

    public Modulo findByIdCurso(UUID id){
        Optional<Modulo> moduloOpt = moduloRepository.findById(id);
        return moduloOpt.get();
    }

    public void createCurso(Modulo modulo){
        moduloRepository.save(modulo);
    }

    public void updateCurso(UUID id, Modulo m){
        Modulo modulo = moduloRepository.getReferenceById(id);
        updateUser(modulo, m);
        moduloRepository.save(modulo);
    }

    public void deleteCurso(UUID id){
        moduloRepository.deleteById(id);
    }

    private void updateUser(Modulo modulo, Modulo m) {
        modulo.setDescricao(m.getDescricao());
        modulo.setAulas(m.getAulas());
        modulo.setNome(m.getNome());
    }

}
