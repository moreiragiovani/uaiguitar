package com.uaiguitar.site.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uaiguitar.site.entidades.Modulo;


public interface ModuloRepository extends JpaRepository<Modulo, UUID>{
    
}
