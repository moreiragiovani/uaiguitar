package com.uaiguitar.site.service;

import java.util.*;

import com.uaiguitar.site.controller.UsuarioController;
import com.uaiguitar.site.entidades.*;
import com.uaiguitar.site.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    ModuloRepository moduloRepository;

    @Autowired
    AulaRepository aulaRepository;

    @Autowired
    CursoCompradoRepository cursoCompradoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    HistoricoAulaRepository historicoAulaRepository;


    public List<Curso> findAllCursos(){
        return cursoRepository.findAll();
    }

    public Curso findBynome(String nome){
        return cursoRepository.findBynome(nome).get();
    }

    public Curso findByIdCurso(UUID id){
        Optional<Curso> userOpt = cursoRepository.findById(id);
        return userOpt.get();
    }

    public void createCurso(Curso user){
        cursoRepository.save(user);
    }

    public Curso updateCurso(Curso c){
        Curso curso = cursoRepository.getReferenceById(c.getId());
        updateUser(curso, c);
        cursoRepository.save(curso);
        return curso;
    }
    private void updateUser (Curso curso, Curso c){
        curso.setNome(c.getNome());
        curso.setDescricao(c.getDescricao());
        curso.setImagem(c.getImagem());
        curso.setVideoApresentacao(c.getVideoApresentacao());
    }

    public void deleteCurso(UUID id, UUID idUser) {
        Set<Aula> listAula = new HashSet<>();
        Set<Modulo> listModulo = new HashSet<>();
        Set<HistoricoAula> listHistorico = new HashSet<>();
        Set<CursoComprado> listCursoP = new HashSet<>();
        Set<CursoComprado> listCursoCpDelete = new HashSet<>();
        Set<HistoricoAula> listHistoricoDelete = new HashSet<>();
        Curso curso = cursoRepository.findById(id).get();
        Usuario user = usuarioRepository.findById(idUser).get();

        for (Modulo m : curso.getModulo()) {
            for (Aula a : m.getAulas()) {
                a.setCurso(null);
                aulaRepository.save(a);
                listAula.add(a);
            }
            m.setAulas(null);
            moduloRepository.save(m);
            listModulo.add(m);
        }
        curso.setModulo(null);
        cursoRepository.save(curso);

        if (!user.getCursosComprados().isEmpty()) {
            for (CursoComprado cp : user.getCursosComprados()) {
                if (!cp.getCursoCompradoId().equals(curso.getId().toString())) {
                    listCursoP.add(cp);
                }
            }
        }

        cursoRepository.delete(curso);

        for (Aula a : listAula) {
            aulaRepository.deleteById(a.getId());
        }
        for (Modulo m : listModulo) {
            moduloRepository.deleteById(m.getId());
        }

        for(Usuario usuario : usuarioRepository.findAll()){
            for(CursoComprado cp : usuario.getCursosComprados()){
                if(!cp.getCursoCompradoId().equals(curso.getId().toString())){
                    listCursoP.add(cp);
                }else {
                    listCursoCpDelete.add(cp);
                }
            }
            for (HistoricoAula h : usuario.getHistoricoAula()){
                if (!h.getCursoHistorico().equals(curso.getId().toString())){
                    listHistorico.add(h);
                }else {
                    listHistoricoDelete.add(h);
                }
            }
            user.setCursosComprados(listCursoP);
            user.setHistoricoAula(listHistorico);
            usuarioRepository.save(user);
        }

        for (CursoComprado cp : listCursoCpDelete) {
            cursoCompradoRepository.deleteById(cp.getId());
        }
        for (HistoricoAula h : listHistoricoDelete) {
            historicoAulaRepository.deleteById(h.getId());
        }
    }
}
