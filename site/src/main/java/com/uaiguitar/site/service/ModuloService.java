package com.uaiguitar.site.service;

import java.util.*;

import com.uaiguitar.site.entidades.Aula;
import com.uaiguitar.site.entidades.Curso;
import com.uaiguitar.site.entidades.HistoricoAula;
import com.uaiguitar.site.repository.AulaRepository;
import com.uaiguitar.site.repository.HistoricoAulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uaiguitar.site.entidades.Modulo;
import com.uaiguitar.site.repository.ModuloRepository;

@Service
public class ModuloService {

    @Autowired
    HistoricoAulaRepository historicoAulaRepository;

    @Autowired
    ModuloRepository moduloRepository;

    @Autowired
    CursoService cursoService;

    @Autowired
    AulaRepository aulaRepository;

    public List<Modulo> findAllModulos(){
        return moduloRepository.findAll();
    }

    public Modulo findByIdModulo(UUID id){
        Optional<Modulo> moduloOpt = moduloRepository.findById(id);
        return moduloOpt.get();
    }

    public Modulo createModulo(Modulo modulo){
        return moduloRepository.save(modulo);
    }

    public void updateModulo(UUID id, Modulo m){
        Modulo modulo = moduloRepository.getReferenceById(id);
        updateModulo(modulo, m);
        moduloRepository.save(modulo);
    }

    public void deleteModulo(UUID id, UUID cursoId){
        Set<Modulo> modulos = new HashSet<>();
        Set<Aula> aulas =  new HashSet<>();

        Curso curso = cursoService.findByIdCurso(cursoId);
        for(Modulo m : curso.getModulo()){
            if(!m.getId().equals(id)){
                modulos.add(m);
            }
            else {
                for(Aula a : m.getAulas()){
                    aulas.add(a);
                }
            }
        }
        curso.setModulo(modulos);
        cursoService.createCurso(curso);
        moduloRepository.deleteById(id);

        aulaRepository.deleteAll(aulas);

        int n = 1000;

        for(Modulo m: curso.getModulo()){
            if(m.getIndiceModulo() < n){
                n = m.getIndiceModulo();
            }
        }

        int p = 1000;
        for(Modulo m : curso.getModulo()){
            if(m.getIndiceModulo().equals(n)){
                for(Aula a : m.getAulas()){
                    if(a.getIndiceDaAula() < p){
                        p = a.getIndiceDaAula();
                    }
                }
            }
        }
        for(Modulo m : curso.getModulo()){
            if(m.getIndiceModulo() == n){
                for(Aula a : m.getAulas()){
                    if(a.getIndiceDaAula() == p){
                        for(HistoricoAula h : historicoAulaRepository.findAll()){
                            if(h.getCursoHistorico().equals(curso.getId().toString())){
                                h.setAulaHistorico(a.getId().toString());
                                historicoAulaRepository.save(h);
                            }
                        }
                    }
                }
            }
        }
    }

    private void updateModulo(Modulo modulo, Modulo m) {
        modulo.setDescricao(m.getDescricao());
        modulo.setAulas(m.getAulas());
        modulo.setNome(m.getNome());
    }

}
