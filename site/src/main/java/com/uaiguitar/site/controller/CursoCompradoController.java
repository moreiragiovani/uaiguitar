package com.uaiguitar.site.controller;

import com.uaiguitar.site.entidades.CursoComprado;
import com.uaiguitar.site.service.CursoCompradoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/curso-comprado")
public class CursoCompradoController {

    CursoCompradoService cursoCompradoService;

    public CursoCompradoController(CursoCompradoService cursoCompradoService) {
        this.cursoCompradoService = cursoCompradoService;
    }

    public CursoComprado createCursoComprado(CursoComprado cursoComprado){
        return cursoCompradoService.createCursoComprado(cursoComprado);
    }

    public List<CursoComprado> findAllCursoComprado(){
        return cursoCompradoService.findAllCursoComprado();
    }
    public void deleteCursoComprado(UUID id){
        cursoCompradoService.deleteCursoComprado(id);
    }
}
