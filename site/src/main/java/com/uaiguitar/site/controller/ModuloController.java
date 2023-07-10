package com.uaiguitar.site.controller;

import java.util.*;

import com.uaiguitar.site.entidades.Curso;
import com.uaiguitar.site.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uaiguitar.site.entidades.Modulo;
import com.uaiguitar.site.service.ModuloService;

@Controller
@RequestMapping("/modulo")
public class ModuloController {
    
    @Autowired
    ModuloService moduloService;

    @Autowired
    CursoService cursoService;

    @Autowired
    CursoController cursoController;

    public ModuloController(ModuloService moduloService, CursoService cursoService) {
        this.moduloService = moduloService;
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<Modulo> findAlModulos(){
        return moduloService.findAllModulos();
    }

    @GetMapping("/{id}")
    public Modulo findByIdModulo(@PathVariable(value = "id") UUID id){
        return moduloService.findByIdModulo(id);
    }

    @PostMapping("/adicionar")
    public String createModulo(Modulo modulo, Model model){
        Set<Modulo> moduloList = new HashSet<>();
        Modulo modulo1 = modulo;
        UUID id = modulo1.getCursoId();
        Curso curso = cursoService.findByIdCurso(id);
        for(Modulo m: curso.getModulo()){
            moduloList.add(m);
        }
        modulo1.setIndiceModulo(curso.getModulo().size() + 1);
        Modulo m1 = moduloService.createModulo(modulo1);
        moduloList.add(modulo1);
        curso.setModulo(moduloList);
        cursoService.updateCurso(id, curso);
        model.addAttribute("modulo", m1);
        return "criar-aula";
    }

    @PostMapping("/cria/aula")
    public String criarAula(Modulo modulo, Model model){
        model.addAttribute("modulo", moduloService.findByIdModulo(modulo.getId()));
        return "criar-aula";
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
