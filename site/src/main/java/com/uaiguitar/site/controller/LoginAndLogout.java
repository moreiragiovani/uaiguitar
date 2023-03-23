package com.uaiguitar.site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


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

}
