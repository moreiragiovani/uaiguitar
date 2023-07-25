package com.uaiguitar.site.service;

import java.util.*;

import com.uaiguitar.site.entidades.Curso;
import com.uaiguitar.site.entidades.HistoricoAula;
import com.uaiguitar.site.entidades.Modulo;
import com.uaiguitar.site.repository.HistoricoAulaRepository;
import com.uaiguitar.site.util.FindFirstAula;
import com.uaiguitar.site.util.LinkVideoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uaiguitar.site.entidades.Aula;
import com.uaiguitar.site.repository.AulaRepository;

@Service
public class AulaService {

    @Autowired
    CursoService cursoService;
    @Autowired
    ModuloService moduloService;
    @Autowired
    AulaRepository aulaRepository;
    @Autowired
    HistoricoAulaRepository historicoAulaRepository;
    LinkVideoConverter lv = new LinkVideoConverter();

    public List<Aula> findAllAula(){
        return aulaRepository.findAll();
    }

    public Aula findByIdAula(UUID id){
        Optional<Aula> aulaOpt = aulaRepository.findById(id);
        return aulaOpt.get();
    }

    public Aula createAula(Aula aula){
        if(!aula.getVideo().isEmpty()){
            String  url = lv.converterLinkVideoToIframe(aula.getVideo());
            aula.setVideo(url);
        }
        Set<Aula> aulasList = new HashSet<>();
        Modulo modulo = moduloService.findByIdModulo(aula.getModuloId());

        for(Aula a: modulo.getAulas()){
            aulasList.add(a);
        }

        aula.setIndiceDaAula(modulo.getAulas().size() + 1);
        aula.setCurso(cursoService.findByIdCurso(modulo.getCursoId()));
        aulasList.add(aula);
        aulaRepository.save(aula);
        modulo.setAulas(aulasList);
        moduloService.updateModulo(modulo);
        return aula;
    }

    public Aula updateAula(Aula aula){
        if(!aula.getVideo().isEmpty()){
            if(!aula.getVideo().contains("embed")){
                String  url = lv.converterLinkVideoToIframe(aula.getVideo());
                aula.setVideo(url);
            }
        }
        Aula a = aulaRepository.getReferenceById(aula.getId());
        updateAula(a, aula);
        aulaRepository.save(a);
        return a;
    }

    public void deleteAula(Aula a){

        Set<Aula> aulaSet = new HashSet<>();
        Modulo modulo = moduloService.findByIdModulo(a.getModuloId());

        for(Aula au: modulo.getAulas()){
            if(!au.getId().equals(a.getId())){
                aulaSet.add(au);
            }
        }

        modulo.setAulas(aulaSet);
        moduloService.updateModulo(modulo);

        FindFirstAula fD = new FindFirstAula();
        Aula aula = aulaRepository.findById(a.getId()).get();
        Curso c1 = cursoService.findByIdCurso(aula.getCurso().getId());
        aulaRepository.deleteById(a.getId());

        for(Modulo m : c1.getModulo()){
            if(m.getIndiceModulo() == fD.indiceAulaMinimo(c1)[0]){
                for(Aula au : m.getAulas()){
                    if(au.getIndiceDaAula() == fD.indiceAulaMinimo(c1)[1]){
                        for(HistoricoAula h : historicoAulaRepository.findAll()){
                            if(h.getAulaHistorico().equals(au.toString())){
                                h.setNomeAula(au.getNome());
                                h.setAulaHistorico(au.getId().toString());
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
