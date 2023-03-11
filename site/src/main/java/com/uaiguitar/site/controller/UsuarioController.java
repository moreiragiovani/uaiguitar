package com.uaiguitar.site.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uaiguitar.site.entidades.Usuario;
import com.uaiguitar.site.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    UsuarioService service;

    @GetMapping
    public List<Usuario> findAllUsuarios(){
        return service.findAllUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario findByUsuario(@PathVariable(value = "id") UUID id){
        return service.findByIdUsuario(id);
    }

    @PostMapping
    public void createUsuario(Usuario usuario){
        service.createUsuario(usuario);
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