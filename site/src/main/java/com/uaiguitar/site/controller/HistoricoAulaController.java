package com.uaiguitar.site.controller;

import com.uaiguitar.site.entidades.Aula;
import com.uaiguitar.site.entidades.HistoricoAula;
import com.uaiguitar.site.entidades.Usuario;
import com.uaiguitar.site.service.HistoricoAulaService;
import com.uaiguitar.site.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;
import java.util.UUID;

@Controller
@RequestMapping("/historico")
public class HistoricoAulaController {

    @Autowired
    HistoricoAulaService service;


    @Autowired
    UsuarioController usuarioController;

    @PostMapping()
    public String criarHistorico(HistoricoAula historicoAula){
        UUID id = null;
        for(HistoricoAula h: idHistorico()){
            if(h.getCursoHistorico() == historicoAula.getCursoHistorico()){
                 id = UUID.fromString(h.getAulaHistorico());
            }
        }
        service.criarHistorico(historicoAula);
        usuarioController.historicoAula(historicoAula);
        if(id != null){
            service.deleteById(id);
        }
        return "redirect:/aula/" + historicoAula.getAulaHistorico();
    }

    public Set<HistoricoAula> idHistorico(){
        try {
            Usuario usuario = usuarioController.findByIdUsuario(usuarioController.logado().getId());
            return usuario.getHistoricoAula();
        }catch (Exception e){
            System.out.println("------------>>>>>>>>>>>>>> ouvi um erro para achar id" + e.getMessage());
            return null;
        }
    }
}
