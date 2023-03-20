package com.uaiguitar.site.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.uaiguitar.site.entidades.Aula;
import com.uaiguitar.site.entidades.Curso;
import com.uaiguitar.site.entidades.Modulo;
import com.uaiguitar.site.entidades.Role;
import com.uaiguitar.site.entidades.Usuario;
import com.uaiguitar.site.enums.RoleNome;
import com.uaiguitar.site.repository.AulaRepository;
import com.uaiguitar.site.repository.CursoRepository;
import com.uaiguitar.site.repository.ModuloRepository;
import com.uaiguitar.site.repository.RoleRepository;
import com.uaiguitar.site.repository.UsuarioRepository;

@Configuration
@Profile("test")
public class CursoConfig implements CommandLineRunner{

    private static final String senha = "$2a$10$GZGhNajfLIL5JK8kcCUktOcBjUfQHdVda4uw2am.zXbVezCorSEre";
    private static final String video = "https://www.youtube.com/watch?v=RYpSXpf0L8k";
    private static final String foto ="picsum.photos/200";

    @Autowired
    AulaRepository aulaRepository;

    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    ModuloRepository moduloRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        
        Set<Aula> aulas = new LinkedHashSet<>();
        List<Modulo> modulos = new ArrayList<>();
        Set<Curso> cursos = new HashSet<>();
        Set<Role> roles = new HashSet<>();

        Role role = new Role(null, RoleNome.ROLE_GRATIS);
        roleRepository.save(role);

        Aula a1 = new Aula(null, "Aula 1", "Nessa aula aprenderemos...", "#", foto, video);
        Aula a2 = new Aula(null, "Aula 2", "Nessa aula aprenderemos...", "#", foto, video);
        Aula a3 = new Aula(null, "Aula 3", "Nessa aula aprenderemos...", "#", foto, video);
        
        aulas.add(a1);
        aulas.add(a2);
        aulas.add(a3);

        Modulo m1 = new Modulo(null, "Módulo 1", "Modulo um como afinar", aulas);
        modulos.add(m1);
        Curso c1 = new Curso(null, "Aulas de violão", "Curso de violão para iniciantes", foto, video, modulos);
        
        cursos.add(c1);
        aulaRepository.saveAll(Arrays.asList(a1, a2, a3));
        moduloRepository.save(m1);
        cursoRepository.save(c1);
        roles.add(role);

        Usuario usuario = new Usuario(null, "giovanigeo", "Giovani Moreira", "giovani_moreira@hotmail.com", senha, cursos, roles);

        usuarioRepository.save(usuario);
    }
    
}
