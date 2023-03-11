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

import com.uaiguitar.site.entidades.Curso;
import com.uaiguitar.site.service.CursoService;

@Controller
public class CursoController {

    @Autowired
    CursoService cursoService;

    @GetMapping
    public List<Curso> findAlCursos(){
        return cursoService.findAllCursos();
    }

    @GetMapping("/{id}")
    public Curso findByIdCurso(@PathVariable(value = "id") UUID id){
        return cursoService.findByIdCurso(id);
    }

    @PostMapping
    public void createCurso(Curso curso){
        cursoService.createCurso(curso);
    }

    @PutMapping("/{id}")
    public void updateCurso(@PathVariable(value = "id") UUID id, Curso curso){
        cursoService.updateCurso(id, curso);
    }

    @DeleteMapping("/{id}")
    public void deleteCurso(@PathVariable(value = "id") UUID id){
        cursoService.deleteCurso(id);
    }
    
}
