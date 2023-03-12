package com.uaiguitar.site.entidades;

import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;

import com.uaiguitar.site.enums.RoleNome;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_role")
public class Role implements GrantedAuthority{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleNome roleNome;

    public Role() {
    }

    public Role(UUID id, RoleNome roleNome) {
        this.id = id;
        this.roleNome = roleNome;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public RoleNome getRoleNome() {
        return roleNome;
    }

    public void setRoleNome(RoleNome roleNome) {
        this.roleNome = roleNome;
    }

    @Override
    public String getAuthority() {
        return this.roleNome.toString();
    }
}
