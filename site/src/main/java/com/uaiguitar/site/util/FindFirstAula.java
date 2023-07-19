package com.uaiguitar.site.util;

import com.uaiguitar.site.entidades.Aula;
import com.uaiguitar.site.entidades.Curso;
import com.uaiguitar.site.entidades.HistoricoAula;
import com.uaiguitar.site.entidades.Modulo;
import com.uaiguitar.site.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;

public class FindFirstAula {

    public FindFirstAula(){}

    public Integer[] indiceAulaMinimo(Curso curso){
        Integer[] numeros = new Integer[2];
        var n = 1000;

        for(Modulo m: curso.getModulo()){
            if(m.getIndiceModulo() < n){
                n = m.getIndiceModulo();
            }
        }
        numeros[0] = n;

        var p = 1000;
        for(Modulo m : curso.getModulo()){
            if(m.getIndiceModulo().equals(n)){
                for(Aula a : m.getAulas()){
                    if(a.getIndiceDaAula() < p){
                        p = a.getIndiceDaAula();
                    }
                }
            }
        }
        numeros[1] = p;
        return numeros;
    }
}
