package com.uaiguitar.site.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.uaiguitar.site.entidades.Curso;
import com.uaiguitar.site.entidades.HistoricoAula;
import com.uaiguitar.site.entidades.Modulo;
import com.uaiguitar.site.repository.HistoricoAulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uaiguitar.site.entidades.Aula;
import com.uaiguitar.site.repository.AulaRepository;

@Service
public class AulaService {

    @Autowired
    CursoService cursoService;
    
    @Autowired
    AulaRepository aulaRepository;

    @Autowired
    HistoricoAulaRepository historicoAulaRepository;

    public List<Aula> findAllAula(){
        return aulaRepository.findAll();
    }

    public Aula findByIdAula(UUID id){
        Optional<Aula> aulaOpt = aulaRepository.findById(id);
        return aulaOpt.get();
    }

    public void createAula(Aula aula){
        aulaRepository.save(aula);
    }

    public void updateAula(UUID id, Aula a){
        Aula curso = aulaRepository.getReferenceById(id);
        updateAula(curso, a);
        aulaRepository.save(curso);
    }

    public void deleteAula(UUID id){
        Aula aula = aulaRepository.findById(id).get();

        Curso c1 = cursoService.findByIdCurso(aula.getCurso().getId());

        aulaRepository.deleteById(id);


        int n = 1000;
        for(Modulo m: c1.getModulo()){
            if(m.getIndiceModulo() < n){
                n = m.getIndiceModulo();
            }
        }

        int p = 1000;
        for(Modulo m : c1.getModulo()){
            if(m.getIndiceModulo().equals(n)){
                for(Aula a : m.getAulas()){
                    if(a.getIndiceDaAula() < p){
                        p = a.getIndiceDaAula();
                    }
                }
            }
        }
        for(Modulo m : c1.getModulo()){
            if(m.getIndiceModulo() == n){
                for(Aula a : m.getAulas()){
                    if(a.getIndiceDaAula() == p){
                        for(HistoricoAula h : historicoAulaRepository.findAll()){
                            if(h.getAulaHistorico().equals(id.toString())){
                                h.setAulaHistorico(a.getId().toString());
                                historicoAulaRepository.save(h);
                            }
                        }
                    }
                }
            }
        }
    }

    private void updateAula(Aula aula, Aula a) {
        aula.setNome(a.getNome());
        aula.setCompletoPdf(a.getCompletoPdf());
        aula.setDescricao(a.getDescricao());
        aula.setImgVideo(a.getImgVideo());
        aula.setVideo(a.getVideo());
    }

}
