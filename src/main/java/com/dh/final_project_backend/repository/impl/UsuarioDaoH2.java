package com.dh.final_project_backend.repository.impl;

import com.dh.final_project_backend.entity.Usuario;
import com.dh.final_project_backend.repository.IDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioDaoH2 implements IDao<Usuario> {


    @Override
    public Usuario guardar(Usuario usuario) {
        return null;
    }

    @Override
    public Usuario buscar(Integer id) {
        return null;
    }

    @Override
    public Usuario actualizar(Usuario usuario) {
        return null;
    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public List<Usuario> buscarTodos() {
        return null;
    }

    @Override
    public void mostrarTodos() {

    }
}
