package com.uaiguitar.site.controller;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.uaiguitar.site.entidades.*;
import com.uaiguitar.site.repository.HistoricoAulaRepository;
import com.uaiguitar.site.service.CursoService;
import com.uaiguitar.site.service.HistoricoAulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uaiguitar.site.dto.UsuarioDto;
import com.uaiguitar.site.service.UsuarioService;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    CursoService cursoService;

    @Autowired
    UsuarioService service;

    @Autowired
    HistoricoAulaService historicoAulaService;

    public UsuarioDetails logado (){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nomeUsuario = auth.getName();
        return service.findByUsername(nomeUsuario);
    }

    @GetMapping("/conta")
    public String usuarioLogado(){
        return "redirect:"+ logado().getId();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> findAllUsuarios(){
        return ResponseEntity.ok().body(service.findAllUsuarios());
    }

    @GetMapping("/{id}")
    public String findByUsuario(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("usuario", service.findByIdUsuario(id));
        return "minha-conta";
    }

    public Usuario findByIdUsuario(UUID id){
        return service.findbyid(id);
    }

    @PostMapping("/criar")
    public String createUsuario(Usuario usuario, RedirectAttributes attributes){
        System.out.println(usuario.getUsername() + "!!!!!!!!!!!!!!");
        service.createUsuario(usuario, attributes);
        return "redirect:login";
    }

    @PutMapping("/{id}")
    public void updateUsuario(@PathVariable(value = "id") UUID id, Usuario user){
        service.updateUsuario(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable(value = "id") UUID id){
        service.deleteUsuario(id);
    }

    @PostMapping("/comprar")
    public String comprandoCurso(Curso curso, Model model) {
        HistoricoAula hist = new HistoricoAula();
        if (usuarioLogado() == null) {
            return "redirect:formulario";
        }
        Curso c1 = cursoService.findByIdCurso(curso.getId());
        int n = 1000;
        for(Modulo m: c1.getModulo()){
            if(m.getIndiceModulo() < n){
                n = m.getIndiceModulo();
            }
        }

        int p = 1000;
        for(Modulo m : c1.getModulo()){
            if(m.getIndiceModulo().equals(n)){
                for(Aula a : m.getAulas()){
                    if(a.getIndiceDaAula() < p){
                        p = a.getIndiceDaAula();
                    }
                }
            }
        }
        System.out.printf("--------------------------- "+n + " " + p);
        for(Modulo m : c1.getModulo()){
            if(m.getIndiceModulo() == n){
                System.out.printf("----------------------------------------------- "+n);
                for(Aula a : m.getAulas()){
                    if(a.getIndiceDaAula() == p){
                        System.out.printf("------------------------->>>>>>>>>>>>>>>>. " + p);
                        hist.setAulaHistorico(a.getId().toString());
                        hist.setCursoHistorico(c1.getId().toString());
                        hist.setNomeCurso(c1.getNome());
                        hist.setNomeAula(a.getNome());
                    }
                }
            }
        }
        service.cursoComprado(logado().getId(), c1);
        service.historicoAulaAtualizado(logado().getId(), historicoAulaService.criarHistorico(hist));
        return "redirect:/aula/"+hist.getAulaHistorico();
    }

    public void historicoAula(UUID id, HistoricoAula historicoAula){
        service.historicoAulaAtualizado(id, historicoAula);
    }
}
