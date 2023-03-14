package com.uaiguitar.site.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uaiguitar.site.dto.UsuarioDto;
import com.uaiguitar.site.entidades.Usuario;
import com.uaiguitar.site.entidades.UsuarioDetails;
import com.uaiguitar.site.repository.UsuarioRepository;

@Service
public class UsuarioService{
 
    @Autowired
    UsuarioRepository usuarioRepository;

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

    public void createUsuario(Usuario user, RedirectAttributes attributes){
        try{
            user.setSenha(new BCryptPasswordEncoder().encode(user.getSenha()));
            usuarioRepository.save(user);
        }catch(Exception e){
            attributes.addFlashAttribute("mensagem", "Nome de Usuario: " + user.getUsername() + 
                                        " já existe.");
            // System.out.println("----- erro criação Servico");
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

    private void updateUser(Usuario usuario, Usuario user) {
        usuario.setUsername(user.getUsername());
        usuario.setNomeCompleto(user.getNomeCompleto());
        usuario.setEmail(user.getEmail());
        usuario.setSenha(user.getSenha());
        
    }

    public UsuarioDto usuarioToUsuarioDtoConverter(Usuario usuario, UsuarioDto usuarioDto){

        usuarioDto.setId(usuario.getId());
        usuarioDto.setNomeCompleto(usuario.getNomeCompleto());
        usuarioDto.setUsername(usuario.getUsername());
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setSenha(usuario.getSenha());
        usuarioDto.setRoles(usuario.getRoles());
        usuarioDto.setCursosComprados(usuario.getCursosComprados());
        
        return usuarioDto;
    }

}
