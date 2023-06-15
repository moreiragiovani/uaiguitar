package com.uaiguitar.site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class LoginAndLogout {
    
    @GetMapping("/logout")
    public String logout() {
	    return "logout";
    }
    @GetMapping("/login")
    public String login() {
	    return "login";
    }


    @GetMapping("/formulario")
    public String formulario(){
        return "formulario";
    }

    @GetMapping("/minha-conta")
    public String minhaConta(){
        return "minha-conta";
    }

    @GetMapping("/criar-curso")
    public String criarCurso(){
        return "criar-curso";
    }

    @GetMapping("/criar-aula")
    public String criarAula(){
        return "criar-aula";
    }

    @GetMapping("/curso/criar-modulo")
    public String criarModulo(){
        return "criar-modulo";
    }

    @GetMapping("curso/aula")
    public String cursoCompleto(){
        return "aula";
    }

}
