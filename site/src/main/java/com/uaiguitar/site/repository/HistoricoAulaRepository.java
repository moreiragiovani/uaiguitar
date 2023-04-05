package com.uaiguitar.site.repository;

import com.uaiguitar.site.entidades.HistoricoAula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HistoricoAulaRepository extends JpaRepository<HistoricoAula, UUID> {
}
