package com.uaiguitar.site.pagSeguroController;

import com.uaiguitar.site.entidades.Curso;
import com.uaiguitar.site.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ComprarController {

    @Autowired
    CursoService cursoService;

    @PostMapping("/curso-dados")
    public String dadosCursoCompra(Curso c, Model model){
        model.addAttribute("curso", cursoService.findByIdCurso(c.getId()));
        return "pedido";
    }


}
