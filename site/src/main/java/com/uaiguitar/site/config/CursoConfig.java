package com.uaiguitar.site.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.uaiguitar.site.entidades.Aula;
import com.uaiguitar.site.entidades.Curso;
import com.uaiguitar.site.entidades.Modulo;
import com.uaiguitar.site.repository.AulaRepository;
import com.uaiguitar.site.repository.CursoRepository;
import com.uaiguitar.site.repository.ModuloRepository;

@Configuration
@Profile("test")
public class CursoConfig implements CommandLineRunner{

    @Autowired
    AulaRepository aulaRepository;

    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    ModuloRepository moduloRepository;

    @Override
    public void run(String... args) throws Exception {
        
        Set<Aula> aulas = new HashSet<>();
        List<Modulo> modulos = new ArrayList<>();
        
        Aula a1 = new Aula(null, "Aula 1", "Nessa aula aprenderemos...", "#", "//#endregion", "//#endregion");
        
        aulas.add(a1);
        Modulo m1 = new Modulo(null, "Módulo 1", "Modulo um como afinar", aulas);
        modulos.add(m1);
        Curso c1 = new Curso(null, "Aulas de violão", "Curso de violão para iniciantes", "//#endregion", "//#endregion", modulos);

        aulaRepository.save(a1);
        moduloRepository.save(m1);
        cursoRepository.save(c1);
    }
    
}
