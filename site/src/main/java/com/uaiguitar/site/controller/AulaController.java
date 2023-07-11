package com.uaiguitar.site.controller;

import java.util.*;

import com.uaiguitar.site.entidades.Curso;
import com.uaiguitar.site.entidades.Modulo;
import com.uaiguitar.site.service.CursoService;
import com.uaiguitar.site.service.ModuloService;
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
    @Autowired
    ModuloService moduloService;
    @Autowired
    CursoController cursoController;

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
    public String createAula(Aula aula, Model model){
        Aula aula1 = aula;
        Set<Aula> aulasList = new HashSet<>();
        Modulo modulo = moduloService.findByIdModulo(aula1.getModuloId());
        for(Aula a: modulo.getAulas()){
            aulasList.add(a);
        }
        aula1.setIndiceDaAula(modulo.getAulas().size() + 1);
        aula1.setCurso(cursoService.findByIdCurso(modulo.getCursoId()));
        aulasList.add(aula1);
        aulaService.createAula(aula1);
        modulo.setAulas(aulasList);
        moduloService.updateModulo(aula1.getModuloId(), modulo);
        UUID id = modulo.getCursoId();
        model.addAttribute("curso", cursoService.findByIdCurso(id));
        return "editar-conteudo";
    }

    @PostMapping("/editar")
    public String editarAula(Aula aula, Model model){
        model.addAttribute("aula", aulaService.findByIdAula(aula.getId()));
        return "editar-aula";
    }

    @PostMapping("/update")
    public String updateAula(Aula aula, Model model){
        aulaService.updateAula(aula.getId(), aula);
        model.addAttribute("curso", cursoService.findByIdCurso(aulaService.findByIdAula(aula.getId()).getCurso().getId()));
        return "editar-conteudo";
    }

    @PostMapping("/apagar")
    public String deleteAula(Aula aula, Model model){
        Set<Aula> aulaSet = new HashSet<>();
        model.addAttribute("curso", cursoService.findByIdCurso(aulaService.findByIdAula(aula.getId()).getCurso().getId()));
        Modulo modulo = moduloService.findByIdModulo(aula.getModuloId());
        for(Aula a: modulo.getAulas()){
            if(!a.getId().equals(aula.getId())){
                aulaSet.add(a);
            }
        }
        modulo.setAulas(aulaSet);
        moduloService.updateModulo(modulo.getId(), modulo);
        aulaService.deleteAula(aula.getId());
        return "editar-conteudo";
    }
}
