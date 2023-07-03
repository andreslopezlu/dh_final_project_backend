package com.dh.final_project_backend.service;

import com.dh.final_project_backend.entity.Usuario;
import com.dh.final_project_backend.entity.UsuarioDTO;
import com.dh.final_project_backend.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioService implements IUsuarioService{

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ObjectMapper mapper;

    @Override
    public Usuario guardar(UsuarioDTO usuarioDTO){
        Usuario usuario = mapper.convertValue(usuarioDTO, Usuario.class);
        return usuarioRepository.save(usuario);
    }

    @Override
    public UsuarioDTO buscar(Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        UsuarioDTO usuarioDTO = null;
        if(usuario.isPresent()){
            usuarioDTO = mapper.convertValue(usuario.get(), UsuarioDTO.class);
        }
        return usuarioDTO;
    }

    @Override
    public Usuario actualizar(UsuarioDTO usuarioDTO){
        Usuario usuario = mapper.convertValue(usuarioDTO, Usuario.class);
        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminar(Long id){
        usuarioRepository.deleteById(id);
    }

    @Override
    public Set<UsuarioDTO> buscarTodos(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        Set<UsuarioDTO> usuariosDTOS = new HashSet<>();
        for(Usuario usuario : usuarios){
            usuariosDTOS.add(mapper.convertValue(usuario, UsuarioDTO.class));
        }
        return usuariosDTOS;
    }

}
