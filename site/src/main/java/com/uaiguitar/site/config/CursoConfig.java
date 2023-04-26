// package com.uaiguitar.site.config;
//
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.HashSet;
// import java.util.LinkedHashSet;
// import java.util.List;
// import java.util.Set;
//
// import com.uaiguitar.site.entidades.*;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Profile;
//
// import com.uaiguitar.site.enums.RoleNome;
// import com.uaiguitar.site.repository.AulaRepository;
// import com.uaiguitar.site.repository.CursoRepository;
// import com.uaiguitar.site.repository.ModuloRepository;
// import com.uaiguitar.site.repository.RoleRepository;
// import com.uaiguitar.site.repository.UsuarioRepository;
//
// @Configuration
// @Profile("test")
// public class CursoConfig implements CommandLineRunner{
//
//     private static final String senha = "$2a$10$GZGhNajfLIL5JK8kcCUktOcBjUfQHdVda4uw2am.zXbVezCorSEre";
//     private static final String video = "https://www.youtube.com/watch?v=RYpSXpf0L8k";
//     private static final String foto ="picsum.photos/200";
//
//     @Autowired
//     AulaRepository aulaRepository;
//
//     @Autowired
//     CursoRepository cursoRepository;
//
//     @Autowired
//     ModuloRepository moduloRepository;
//
//     @Autowired
//     UsuarioRepository usuarioRepository;
//
//     @Autowired
//     RoleRepository roleRepository;
//
//     @Override
//     public void run(String... args) throws Exception {
//
//         Set<Aula> aulas = new LinkedHashSet<>();
//         List<Modulo> modulos = new ArrayList<>();
//         Set<Curso> cursos = new HashSet<>();
//         Set<Role> roles = new HashSet<>();
//
//
////         Role role2 = new Role(null, RoleNome.ROLE_GRATIS);
////         roleRepository.save(role2);
////         roles.add(role2);
//
//
//
//         Aula a1 = new Aula(null, 1, "Aula 1", "Nessa aula aprenderemos...", "#", foto, video, null);
//         Aula a2 = new Aula(null, 2, "Aula 2", "Nessa aula aprenderemos...", "#", foto, video, null);
//         Aula a3 = new Aula(null, 3, "Aula 3", "Nessa aula aprenderemos...", "#", foto, video, null);
//
//         aulas.add(a1);
//         aulas.add(a2);
//         aulas.add(a3);
//         aulaRepository.saveAll(Arrays.asList(a1, a2, a3));
//
//
//         Modulo m1 = new Modulo(null, 1, "Módulo 1", "Modulo um como afinar", aulas);
//         moduloRepository.save(m1);
//         modulos.add(m1);
//
//
//
//         Curso c1 = new Curso(null, "Aulas de guitarra", "Curso de banjo para iniciantes", foto, video, modulos);
//         cursoRepository.save(c1);
//
//         a1.setCurso(c1);
//         a2.setCurso(c1);
//         a3.setCurso(c1);
//
//         aulas.add(a1);
//         aulas.add(a2);
//         aulas.add(a3);
//
//         cursos.add(c1);
//         aulaRepository.saveAll(Arrays.asList(a1, a2, a3));
//         moduloRepository.save(m1);
//         cursoRepository.save(c1);
//
////         Usuario usuario = new Usuario(null, "geraldo", "Geraldo Moreira", "geraldo_moreira@hotmail.com", senha, cursos, roles,null);
////
////         usuarioRepository.save(usuario);
//     }
//
// }
