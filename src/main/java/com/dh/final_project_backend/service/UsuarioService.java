package com.dh.final_project_backend.service;

import com.dh.final_project_backend.entity.Usuario;
import com.dh.final_project_backend.repository.IDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private IDao<Usuario> usuarioDao;

    public UsuarioService() {
    }

    public IDao<Usuario> getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(IDao<Usuario> usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public Usuario guardarUsuario(Usuario usuario){
        return usuarioDao.guardar(usuario);
    }

    public Usuario buscarUsuario (Integer id){
        return usuarioDao.buscar(id);
    }

    public Usuario actualizarUsuario (Usuario usuario){
        return usuarioDao.actualizar(usuario);
    }

    public void eliminarUsuario (Integer id){
        usuarioDao.eliminar(id);
    }

    public List<Usuario> buscarTodosUsuarios(){
        return usuarioDao.buscarTodos();
    }

}
