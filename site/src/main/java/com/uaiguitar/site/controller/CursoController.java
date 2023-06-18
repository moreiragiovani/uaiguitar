package com.uaiguitar.site.controller;

import java.util.UUID;

import com.uaiguitar.site.entidades.*;
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

    @GetMapping("/todos")
    public String findAlCursos(Model model) {
        model.addAttribute("cursos", cursoService.findAllCursos());
//        if(usuarioController.logado().isAccountNonLocked()){
//            return "redirect:/usuario/conta";
//        }
        return "todos-os-cursos";
    }

    @GetMapping("/{id}")
    public String findByIdCurso(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("aula", cursoService.findByIdCurso(id));
        return "aula";
    }

    @PostMapping
    public String createCurso(Curso curso, Model model){
        String nomeCurso = curso.getNome();
        cursoService.createCurso(curso);
        return editarCurso(model);
    }

    @PutMapping("/{id}")
    public void updateCurso(Curso curso){
        cursoService.updateCurso(curso.getId(), curso);
    }

    @DeleteMapping("/{id}")
    public void deleteCurso(@PathVariable(value = "id") UUID id){
        cursoService.deleteCurso(id);
    }

    @GetMapping("/editar")
    public String editarCurso(Model model){
        model.addAttribute("cursos", cursoService.findAllCursos());
        return "editar-curso";
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
