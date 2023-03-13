package com.uaiguitar.site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginAndLogout {
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:login";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/formulario")
    public String formulario(){
        return "formulario";
    }

}
