package com.uaiguitar.site.controller;

import com.uaiguitar.site.entidades.Aula;
import com.uaiguitar.site.entidades.Curso;
import com.uaiguitar.site.entidades.HistoricoAula;
import com.uaiguitar.site.entidades.Usuario;
import com.uaiguitar.site.service.HistoricoAulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
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
    public String criarHistorico(HistoricoAula historicoAula){
        UUID id = null;
        for(Curso c: idHistorico()){
            if(c.getHistoricoAula() == null){
                service.criarHistorico(historicoAula);
                cursoController.historicoAula(historicoAula);
                return "redirect:/aula/" + historicoAula.getAulaHistorico();
            }
            else {
                if(c.getHistoricoAula().getCursoHistorico().equals(historicoAula.getCursoHistorico())){
                    id = c.getHistoricoAula().getId();
                }
            }
        }
        service.criarHistorico(historicoAula);
        cursoController.historicoAula(historicoAula);

        if(id != null){
            service.deleteById(id);
        }
        return "redirect:/aula/" + historicoAula.getAulaHistorico();
    }

    public Set<Curso> idHistorico(){
        try {
            Usuario usuario = usuarioController.findByIdUsuario(usuarioController.logado().getId());
            return usuario.getCursosComprados();
        }catch (Exception e){
            System.out.println("------------>>>>>>>>>>>>>> ouvi um erro para achar id" + e.getMessage());
            return null;
        }
    }
}
