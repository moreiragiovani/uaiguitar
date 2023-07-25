package com.uaiguitar.site.controller;

import java.util.*;

import com.uaiguitar.site.entidades.Modulo;
import com.uaiguitar.site.service.CursoService;
import com.uaiguitar.site.service.ModuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uaiguitar.site.entidades.Aula;
import com.uaiguitar.site.service.AulaService;
import com.uaiguitar.site.util.LinkVideoConverter;

@Controller
@RequestMapping("/aula")
public class AulaController {

    @Autowired
    CursoService cursoService;
    @Autowired
    AulaService aulaService;
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
        model.addAttribute("curso", cursoService.findByIdCurso(aulaService.createAula(aula).getCurso().getId()));
        return "editar-conteudo";
    }

    @PostMapping("/editar")
    public String editarAula(Aula aula, Model model){
        model.addAttribute("aula", aulaService.findByIdAula(aula.getId()));
        return "editar-aula";
    }

    @PostMapping("/update")
    public String updateAula(Aula aula, Model model){
        model.addAttribute("curso", cursoService.findByIdCurso(aulaService.updateAula(aula).getCurso().getId()));
        return "editar-conteudo";
    }

    @PostMapping("/apagar")
    public String deleteAula(Aula aula, Model model){
        model.addAttribute("curso", cursoService.findByIdCurso(aulaService.findByIdAula(aula.getId()).getCurso().getId()));
        aulaService.deleteAula(aula);
        return "editar-conteudo";
    }
}
