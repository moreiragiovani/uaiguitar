package com.uaiguitar.site.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.uaiguitar.site.entidades.Curso;
import com.uaiguitar.site.entidades.HistoricoAula;
import com.uaiguitar.site.entidades.Modulo;
import com.uaiguitar.site.repository.HistoricoAulaRepository;
import com.uaiguitar.site.util.FindFirstAula;
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
        Aula aula = aulaRepository.getReferenceById(id);
        updateAula(aula, a);
        aulaRepository.save(aula);
    }

    public void deleteAula(UUID id){
        FindFirstAula fD = new FindFirstAula();
        Aula aula = aulaRepository.findById(id).get();
        Curso c1 = cursoService.findByIdCurso(aula.getCurso().getId());
        aulaRepository.deleteById(id);

        for(Modulo m : c1.getModulo()){
            if(m.getIndiceModulo() == fD.indiceAulaMinimo(c1)[0]){
                for(Aula a : m.getAulas()){
                    if(a.getIndiceDaAula() == fD.indiceAulaMinimo(c1)[1]){
                        for(HistoricoAula h : historicoAulaRepository.findAll()){
                            if(h.getAulaHistorico().equals(id.toString())){
                                h.setNomeAula(a.getNome());
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
