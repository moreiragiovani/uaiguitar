package com.uaiguitar.site.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uaiguitar.site.entidades.Curso;
import com.uaiguitar.site.service.CursoService;

@Controller
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    CursoService cursoService;

    @GetMapping("/meus-cursos")
    public String findAlCursos(Model model){
        model.addAttribute("curso", cursoService.findAllCursos());
        return "meus-cursos";

    }

    @GetMapping("/{id}")
    public String findByIdCurso(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("aula", cursoService.findByIdCurso(id));
        return "redirect:/aula";

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
