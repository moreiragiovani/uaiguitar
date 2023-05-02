package com.uaiguitar.site.controller;

import java.util.List;
import java.util.UUID;

import com.uaiguitar.site.entidades.Curso;
import com.uaiguitar.site.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    CursoService cursoService;
    @Autowired
    AulaService aulaService;

    @GetMapping
    public String findAlAulas(Model model){
        model.addAttribute("aula", aulaService.findAllAula());
        return "aula";
    }

    @GetMapping("/{id}")
    public String findByIdAula(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("aula", aulaService.findByIdAula(id));
        return "aula";

    }

    @PostMapping("/adicionar")
    public String createAula(Aula aula){
        System.out.println(aula.getNome() + "!!!!!!!!!!!!!");
//        aula.setCurso(cursoService.findBynome(aula.getCurso().getNome()));
        aulaService.createAula(aula);
        return "redirect:/criar-aula";
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
