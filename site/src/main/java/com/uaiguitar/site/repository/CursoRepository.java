package com.uaiguitar.site.repository;

import java.util.Optional;
import java.util.UUID;

import com.uaiguitar.site.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import com.uaiguitar.site.entidades.Curso;

public interface CursoRepository extends JpaRepository<Curso, UUID>{
    public Optional<Curso> findBynome (String nome);

}
