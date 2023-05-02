package com.uaiguitar.site.controller;

import java.util.UUID;

import com.uaiguitar.site.entidades.UsuarioDetails;
import com.uaiguitar.site.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    UsuarioService usuarioService;

    private static Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    @Autowired
    CursoService cursoService;

    @GetMapping("/todos")
    public String findAlCursos(Model model) {
        model.addAttribute("cursos", cursoService.findAllCursos());
        UsuarioDetails usuario = usuarioService.findByUsername(authentication.getName());

        if(usuario.getRoles().equals("ADMIN")){
            return "cursos-admin";
        }
        return "todos-os-cursos";
    }

//    PEGAR UM CURSO PELO NOME E MANDAR PARA AREA DE EDÇÃO E CRIAÇÃO DE CONTEUDO, MODULOS E AULAS.
    @GetMapping("/{id}")
    public String findByIdCurso(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("aula", cursoService.findByIdCurso(id));
        return "redirect:/aula";

    }

    public String findBynomeCurso(String nome, Model model){
        Curso curso = cursoService.findBynome(nome);
        model.addAttribute("curso", curso);
        return "criar-conteudo";
    }

    @PostMapping
    public String createCurso(Curso curso){
        cursoService.createCurso(curso);
        return findBynomeCurso(curso.getNome(), null);
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
