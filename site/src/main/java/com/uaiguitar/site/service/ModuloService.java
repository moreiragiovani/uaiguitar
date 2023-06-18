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

    public List<Modulo> findAllModulos(){
        return moduloRepository.findAll();
    }

    public Modulo findByIdModulo(UUID id){
        Optional<Modulo> moduloOpt = moduloRepository.findById(id);
        return moduloOpt.get();
    }

    public Modulo createModulo(Modulo modulo){
        return moduloRepository.save(modulo);
    }

    public void updateModulo(UUID id, Modulo m){
        Modulo modulo = moduloRepository.getReferenceById(id);
        updateModulo(modulo, m);
        moduloRepository.save(modulo);
    }

    public void deleteModulo(UUID id){
        moduloRepository.deleteById(id);
    }

    private void updateModulo(Modulo modulo, Modulo m) {
        modulo.setDescricao(m.getDescricao());
        modulo.setAulas(m.getAulas());
        modulo.setNome(m.getNome());
    }

}
