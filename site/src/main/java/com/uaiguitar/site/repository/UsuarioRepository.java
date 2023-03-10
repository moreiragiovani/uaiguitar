package com.uaiguitar.site.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uaiguitar.site.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID>{
    
}
