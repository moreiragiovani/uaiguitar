package com.uaiguitar.site.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.uaiguitar.site.entidades.*;
import com.uaiguitar.site.enums.RoleNome;
import com.uaiguitar.site.repository.HistoricoAulaRepository;
import com.uaiguitar.site.repository.RoleRepository;
import com.uaiguitar.site.service.CursoService;
import com.uaiguitar.site.service.HistoricoAulaService;
import com.uaiguitar.site.util.FindFirstAula;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @Autowired
    RoleRepository roleRepository;

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

    @GetMapping("/formulario")
    public String formulario(Usuario usuario){
        return "formulario";
    }

    @PostMapping("/criar")
    public String createUsuario(@Valid Usuario usuario, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("usuario", usuario);
            return "/formulario";
        }
        service.createUsuario(usuario);
        return "redirect:/login";
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
        return "redirect:/aula/"+service.cursoComprado(logado().getId(), curso).getAulaHistorico();
    }

    public void historicoAula(UUID id, HistoricoAula historicoAula){
        service.historicoAulaAtualizado(id, historicoAula);
    }
}
