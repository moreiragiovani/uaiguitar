package com.uaiguitar.site.service;

import com.uaiguitar.site.entidades.HistoricoAula;
import com.uaiguitar.site.repository.HistoricoAulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HistoricoAulaService {

    @Autowired
    HistoricoAulaRepository repository;

    public HistoricoAula criarHistorico(HistoricoAula historicoAula){return repository.save(historicoAula);}

    public void deleteById(UUID id){repository.deleteById(id);}
}
