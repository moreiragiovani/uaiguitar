package com.uaiguitar.site.controller;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.uaiguitar.site.entidades.*;
import com.uaiguitar.site.service.CursoCompradoService;
import com.uaiguitar.site.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.uaiguitar.site.service.CursoService;

@Controller
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    UsuarioController usuarioController;

    @Autowired
    CursoService cursoService;

    @Autowired
    CursoCompradoService cursoCompradoService;

    @GetMapping("/todos")
    public String findAlCursos(Model model) {
        model.addAttribute("cursos", cursoService.findAllCursos());
        return "todos-os-cursos";
    }

    @GetMapping("/{id}")
    public String findByIdCurso(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("aula", cursoService.findByIdCurso(id));
        return "aula";
    }

    @PostMapping
    public String createCurso(Curso curso, Model model){
        cursoService.createCurso(curso);
        return editarCurso(model);
    }

    @PostMapping("/update")
    public String updateCurso(Curso curso, Model model){
        model.addAttribute("curso", cursoService.updateCurso(curso));
        return "editar-conteudo";
    }

    @PostMapping("/editar")
    public String editarAula(Curso curso, Model model){
        model.addAttribute("curso", cursoService.findByIdCurso(curso.getId()));
        return "editar-curso";
    }

    @PostMapping("/deletar")
    public String deleteCurso(Curso curso, Model model){
        Usuario usuario = usuarioController.findByIdUsuario(usuarioController.logado().getId());
        cursoService.deleteCurso(curso.getId(), usuario.getId());
        model.addAttribute("usuario", usuario);
        return "minha-conta";
    }

    @GetMapping("/editar/cursos")
    public String editarCurso(Model model){
        model.addAttribute("cursos", cursoService.findAllCursos());
        return "cursos-editar";
    }

    @PostMapping("/criar/conteudo")
    public String criarConteudo(Curso curso, Model model){
        model.addAttribute("curso", cursoService.findByIdCurso(curso.getId()));
        return "editar-conteudo";
    }

    @PostMapping("/cria/modulo")
    public String criaModulo(Curso curso, Model model){
        model.addAttribute("curso", cursoService.findByIdCurso(curso.getId()));
        return "criar-modulo";
    }
}
