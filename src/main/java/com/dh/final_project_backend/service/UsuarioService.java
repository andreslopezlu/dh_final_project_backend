package com.dh.final_project_backend.service;

import com.dh.final_project_backend.entity.Usuario;
import com.dh.final_project_backend.repository.IDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private IDao<Usuario> usuarioDao;

    public UsuarioService(IDao<Usuario> usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public IDao<Usuario> getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(IDao<Usuario> usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public Usuario guardar(Usuario usuario){
        return usuarioDao.guardar(usuario);
    }

    public Usuario buscar(Integer id){
        return usuarioDao.buscar(id);
    }

    public Usuario actualizar(Usuario usuario){
        return usuarioDao.actualizar(usuario);
    }

    public void eliminar(Integer id){
        usuarioDao.eliminar(id);
    }

    public List<Usuario> buscarTodos(){
        return usuarioDao.buscarTodos();
    }

}
