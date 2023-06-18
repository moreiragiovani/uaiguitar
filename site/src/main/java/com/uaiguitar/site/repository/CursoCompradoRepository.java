package com.uaiguitar.site.repository;

import com.uaiguitar.site.entidades.CursoComprado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CursoCompradoRepository extends JpaRepository<CursoComprado, UUID> {
}
