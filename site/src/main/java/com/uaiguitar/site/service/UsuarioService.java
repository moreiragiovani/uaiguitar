package com.uaiguitar.site.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uaiguitar.site.dto.UsuarioDto;
import com.uaiguitar.site.entidades.Usuario;
import com.uaiguitar.site.repository.UsuarioRepository;

@Service
public class UsuarioService {
 
    @Autowired
    UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioDto findByUsername(String username){

        UsuarioDto usuarioDto = new UsuarioDto();

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

    public List<UsuarioDto> findAllUsuarios(){
        UsuarioDto usuarioDto = new UsuarioDto();

        List<UsuarioDto> userListDto = new ArrayList<>();

        List<Usuario> userList = usuarioRepository.findAll();

        for(Usuario u: userList){
            usuarioDto.setNomeCompleto(u.getNomeCompleto());
            usuarioDto.setEmail(u.getEmail());
            usuarioDto.setId(u.getId());
            usuarioDto.setUsername(u.getUsername());
            usuarioDto.setCursosComprados(u.getCursosComprados());
            usuarioDto.setSenha(u.getSenha());
            usuarioDto.setRoles(u.getRoles());
            userListDto.add(usuarioDto);
            
        }
        return userListDto;
    }

    public UsuarioDto findByIdUsuario(UUID id){

        UsuarioDto usuarioDto = new UsuarioDto();

        Optional<Usuario> userOpt = usuarioRepository.findById(id);

        Usuario u = userOpt.get();

        usuarioDto.setNomeCompleto(u.getNomeCompleto());
        usuarioDto.setEmail(u.getEmail());
        usuarioDto.setId(u.getId());
        usuarioDto.setUsername(u.getUsername());
        usuarioDto.setSenha(u.getSenha());
        usuarioDto.setCursosComprados(u.getCursosComprados());
        usuarioDto.setRoles(u.getRoles());

        return usuarioDto;
    }

    public void createUsuario(Usuario user){
        usuarioRepository.save(user);
    }

    public void updateUsuario(UUID id, Usuario user){
        Usuario usuario = usuarioRepository.getReferenceById(id);
        updateUser(usuario, user);
        usuarioRepository.save(usuario);
    }

    public void deleteUsuario(UUID id){
        usuarioRepository.deleteById(id);
    }

    private void updateUser(Usuario usuario, Usuario user) {
        usuario.setUsername(user.getUsername());
        usuario.setNomeCompleto(user.getNomeCompleto());
        usuario.setEmail(user.getEmail());
        usuario.setSenha(user.getSenha());
        
    }

}
