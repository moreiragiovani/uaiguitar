package com.uaiguitar.site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class fragments {
    
    @GetMapping("/side-bar")
    public String sidebar(){
        return "side-bar";
    }
}
