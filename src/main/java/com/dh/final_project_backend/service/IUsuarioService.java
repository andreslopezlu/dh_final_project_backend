package com.dh.final_project_backend.service;

import com.dh.final_project_backend.entity.Usuario;
import com.dh.final_project_backend.entity.UsuarioDTO;

import java.util.Set;

public interface IUsuarioService {

    Usuario guardar(UsuarioDTO usuarioDTO);
    UsuarioDTO buscar(Long id);
    Usuario actualizar(UsuarioDTO usuarioDTO);
    void eliminar(Long id);
    Set<UsuarioDTO> buscarTodos();

}
