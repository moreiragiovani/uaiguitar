package com.uaiguitar.site.controller;

import java.util.List;
import java.util.UUID;

import com.uaiguitar.site.entidades.HistoricoAula;
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
import com.uaiguitar.site.entidades.Usuario;
import com.uaiguitar.site.service.UsuarioService;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    UsuarioService service;

    public UUID logado (){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nomeUsuario = auth.getName();
        return service.findByUsername(nomeUsuario).getId();
    }

    public void historicoAula(HistoricoAula historicoAula){
        service.historicoAulaAtualizado(logado(), historicoAula);
    }

    @GetMapping("/conta")
    public String usuarioLogado(){
        return "redirect:"+ logado();
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

    @PostMapping
    public String createUsuario(Usuario usuario, RedirectAttributes attributes){
        service.createUsuario(usuario, attributes);
        return "redirect:formulario"; 
    }

    @PutMapping("/{id}")
    public void updateUsuario(@PathVariable(value = "id") UUID id, Usuario user){
        service.updateUsuario(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable(value = "id") UUID id){
        service.deleteUsuario(id);
    }    
}
