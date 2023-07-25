package com.uaiguitar.site.service;

import java.util.*;

import com.uaiguitar.site.controller.CursoCompradoController;
import com.uaiguitar.site.entidades.*;
import com.uaiguitar.site.enums.RoleNome;
import com.uaiguitar.site.repository.CursoCompradoRepository;
import com.uaiguitar.site.repository.RoleRepository;
import com.uaiguitar.site.util.FindFirstAula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uaiguitar.site.dto.UsuarioDto;
import com.uaiguitar.site.repository.UsuarioRepository;

@Service
public class UsuarioService{
 
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CursoCompradoController cursoCompradoController;

    @Autowired
    CursoService cursoService;

    @Autowired
    HistoricoAulaService historicoAulaService;

    @Autowired
    RoleRepository roleRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioDetails findByUsername(String username){

        UsuarioDetails usuarioDto = new UsuarioDetails();
        Optional<Usuario> userOpt = usuarioRepository.findByusername(username);
        Usuario u = userOpt.get();

        usuarioDto.setNomeCompleto(u.getNomeCompleto());
        usuarioDto.setEmail(u.getEmail());
        usuarioDto.setId(u.getId());
        usuarioDto.setUsername(u.getUsername());
        usuarioDto.setRoles(u.getRoles());
        usuarioDto.setSenha(u.getSenha());

        return usuarioDto;
    }

    public Usuario findbyid(UUID id) {
        return usuarioRepository.findById(id).get();
    }

    public List<UsuarioDto> findAllUsuarios(){

        List<UsuarioDto> usuarioDtoList = new ArrayList<>();
        List<Usuario> usuarioList = usuarioRepository.findAll();
        for(Usuario u: usuarioList){
            usuarioDtoList.add(usuarioToUsuarioDtoConverter(u, new UsuarioDto()));
        }

        return usuarioDtoList;
    }

    public UsuarioDto findByIdUsuario(UUID id){

        UsuarioDto usuarioDto = new UsuarioDto();
        Optional<Usuario> userOpt = usuarioRepository.findById(id);
        usuarioDto = usuarioToUsuarioDtoConverter(userOpt.get(), usuarioDto);

        return usuarioDto;
    }

    public void createUsuario(Usuario user){
        try{
            Role role = new Role();
            role.setRoleNome(RoleNome.ROLE_ADMIN);
            roleRepository.save(role);
            Set<Role> roles = new HashSet<>();
//            for (Role r : roleRepository.findAll()){
//                if(r.getRoleNome().equals(RoleNome.ROLE_USER)){
//                    roles.add(r);
//                }
//            }
            roles.add(role);
            user.setRoles(roles);
            user.setSenha(new BCryptPasswordEncoder().encode(user.getSenha()));
            usuarioRepository.save(user);
        }catch(Exception e){
            System.out.printf("Exception usuario existente " + e.getMessage());
        }
    }

    public void updateUsuario(UUID id, Usuario user){
        Usuario usuario = usuarioRepository.getReferenceById(id);
        updateUser(usuario, user);
        usuarioRepository.save(usuario);
    }

    public void deleteUsuario(UUID id){
        usuarioRepository.deleteById(id);
    }

    public HistoricoAula cursoComprado(UUID id, Curso curso1){
        FindFirstAula fD = new FindFirstAula();
        HistoricoAula hist = new HistoricoAula();
        Curso curso = cursoService.findByIdCurso(curso1.getId());
        Usuario usuario = usuarioRepository.findById(id).get();
        CursoComprado cursoComprado = new CursoComprado();
        cursoComprado.setCursoCompradoId(curso.getId().toString());
        Set<CursoComprado> listC = new HashSet<>();
        Set<HistoricoAula> listHistorico = new HashSet<>();

        for(Modulo m : curso.getModulo()){
            if(m.getIndiceModulo() == fD.indiceAulaMinimo(curso)[0]){
                for(Aula a : m.getAulas()){
                    if(a.getIndiceDaAula() == fD.indiceAulaMinimo(curso)[1]){
                        hist.setAulaHistorico(a.getId().toString());
                        hist.setCursoHistorico(curso.getId().toString());
                        hist.setNomeCurso(curso.getNome());
                        hist.setNomeAula(a.getNome());
                    }
                }
            }
        }
        int temp = 0;

        if(!usuario.getCursosComprados().isEmpty()){
            for(CursoComprado c : usuario.getCursosComprados()){
                if(c.getCursoCompradoId().equals(curso.getId().toString())){
                    c.setCursoCompradoId(curso.getId().toString());
                    cursoCompradoController.createCursoComprado(c);
                    temp = 1;
                }
                listC.add(c);
            }
            if(temp == 1){
                usuario.setCursosComprados(listC);
                usuarioRepository.save(usuario);
            }else {
                CursoComprado cP = cursoCompradoController.createCursoComprado(cursoComprado);
                listC.add(cP);
                usuario.setCursosComprados(listC);
                usuarioRepository.save(usuario);
            }
            for (HistoricoAula h: usuario.getHistoricoAula()){
                if(h.getCursoHistorico().equals(hist.getCursoHistorico())){
                    h.setAulaHistorico(hist.getAulaHistorico());
                    h.setNomeAula(hist.getNomeAula());
                    historicoAulaService.criarHistorico(h);
                }
                else {
                    historicoAulaService.criarHistorico(hist);
                }
            }

        }else {
            CursoComprado cP = cursoCompradoController.createCursoComprado(cursoComprado);
            listC.add(cP);

            for (HistoricoAula a: usuarioRepository.findById(usuario.getId()).get().getHistoricoAula()){
                listHistorico.add(a);
            }
            listHistorico.add(historicoAulaService.criarHistorico(hist));
            usuario.setHistoricoAula(listHistorico);
            usuario.setCursosComprados(listC);
            usuarioRepository.save(usuario);
        }
        return hist;
    }

    public void historicoAulaAtualizado(UUID id, HistoricoAula historicoAula){
        Set<HistoricoAula> hList = new HashSet<>();
        Usuario usuario = usuarioRepository.findById(id).get();
        hList.add(historicoAula);
        if(!usuario.getHistoricoAula().isEmpty()){
            for(HistoricoAula h : usuario.getHistoricoAula()){
                if(!h.getCursoHistorico().equals(historicoAula.getCursoHistorico())){
                    hList.add(h);
                }
            }
        }
        usuario.setHistoricoAula(hList);
        usuarioRepository.save(usuario);
    }

    private void updateUser(Usuario usuario, Usuario user) {
        usuario.setUsername(user.getUsername());
        usuario.setNomeCompleto(user.getNomeCompleto());
        usuario.setEmail(user.getEmail());
        usuario.setSenha(user.getSenha());
        usuario.setHistoricoAula(user.getHistoricoAula());
    }

    public UsuarioDto usuarioToUsuarioDtoConverter(Usuario usuario, UsuarioDto usuarioDto){

        usuarioDto.setId(usuario.getId());
        usuarioDto.setNomeCompleto(usuario.getNomeCompleto());
        usuarioDto.setUsername(usuario.getUsername());
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setSenha(usuario.getSenha());
        usuarioDto.setRoles(usuario.getRoles());
        usuarioDto.setHistoricoAula(usuario.getHistoricoAula());
        usuarioDto.setCursosComprados(usuario.getCursosComprados());

        return usuarioDto;
    }
}
