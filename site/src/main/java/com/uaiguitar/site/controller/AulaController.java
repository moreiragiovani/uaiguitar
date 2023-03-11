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
import org.springframework.web.bind.annotation.RequestMapping;

import com.uaiguitar.site.entidades.Aula;
import com.uaiguitar.site.service.AulaService;

@Controller
@RequestMapping("/aula")
public class AulaController {
    
    @Autowired
    AulaService aulaService;

    @GetMapping
    public List<Aula> findAlAulas(){
        return aulaService.findAllAula();
    }

    @GetMapping("/{id}")
    public Aula findByIdAula(@PathVariable(value = "id") UUID id){
        return aulaService.findByIdAula(id);
    }

    @PostMapping
    public void createAula(Aula aula){
        aulaService.createAula(aula);
    }

    @PutMapping("/{id}")
    public void updateAula(@PathVariable(value = "id") UUID id, Aula aula){
        aulaService.updateAula(id, aula);
    }

    @DeleteMapping("/{id}")
    public void deleteAula(@PathVariable(value = "id") UUID id){
        aulaService.deleteAula(id);
    }
}
