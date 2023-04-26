package com.uaiguitar.site.pagSeguroController;

import com.uaiguitar.site.entidades.Curso;
import com.uaiguitar.site.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
public class SeparandoCursoController {

    @Autowired
    CursoService cursoService;

    @GetMapping("/curso-escolhido/{id}")
    public String cursoEscolhido(@PathVariable String id, Model model){
        Curso curso = cursoService.findByIdCurso(UUID.fromString(id));
        model.addAttribute("curso", curso);
        return "Pagina para dados de compra aqui";
    }
}
