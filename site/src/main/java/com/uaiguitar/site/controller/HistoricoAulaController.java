package com.uaiguitar.site.controller;

import com.uaiguitar.site.entidades.*;
import com.uaiguitar.site.service.HistoricoAulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Controller
@RequestMapping("/historico")
public class HistoricoAulaController {

    @Autowired
    HistoricoAulaService service;

    @Autowired
    CursoController cursoController;

    @Autowired
    UsuarioController usuarioController;

    @PostMapping()
    public String criarHistorico(HistoricoAula historicoAula) {
        Usuario usuario = usuarioController.findByIdUsuario(usuarioController.logado().getId());
        int temp = 0;

        if(!usuario.getHistoricoAula().isEmpty()){
            for(HistoricoAula h : usuario.getHistoricoAula()){
                if(h.getCursoHistorico().equals(historicoAula.getCursoHistorico())){
                    h.setCursoHistorico(historicoAula.getCursoHistorico());
                    h.setAulaHistorico(historicoAula.getAulaHistorico());
                    h.setNomeAula(historicoAula.getNomeAula());
                    h.setNomeCurso(historicoAula.getNomeCurso());
                    service.criarHistorico(h);
                    temp = 1;
                }
            }

            if(temp == 0){
                usuarioController.historicoAula(usuarioController.logado().getId(), service.criarHistorico(historicoAula));
            }
        }else{
            usuarioController.historicoAula(usuarioController.logado().getId(), service.criarHistorico(historicoAula));
        }
        return "redirect:/aula/" + historicoAula.getAulaHistorico();
    }
}
