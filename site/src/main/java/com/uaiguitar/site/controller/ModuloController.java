package com.uaiguitar.site.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.uaiguitar.site.entidades.Modulo;
import com.uaiguitar.site.service.ModuloService;

@Controller
public class ModuloController {
    
    @Autowired
    ModuloService moduloService;

    @GetMapping
    public List<Modulo> findAlModulos(){
        return moduloService.findAllModulos();
    }

    @GetMapping("/{id}")
    public Modulo findByIdModulo(@PathVariable(value = "id") UUID id){
        return moduloService.findByIdModulo(id);
    }

    @PostMapping
    public void createModulo(Modulo modulo){
        moduloService.createModulo(modulo);
    }

    @PutMapping("/{id}")
    public void updateModulo(@PathVariable(value = "id") UUID id, Modulo modulo){
        moduloService.updateModulo(id, modulo);
    }

    @DeleteMapping("/{id}")
    public void deleteModulo(@PathVariable(value = "id") UUID id){
        moduloService.deleteModulo(id);
    }
}
