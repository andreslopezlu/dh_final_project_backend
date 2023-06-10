package com.dh.final_project_backend.repository.impl;

import com.dh.final_project_backend.entity.Odontologo;
import com.dh.final_project_backend.repository.IDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OdontologoDaoH2 implements IDao<Odontologo> {
    @Override
    public Odontologo guardar(Odontologo odontologo) {
        return null;
    }

    @Override
    public Odontologo buscar(Integer id) {
        return null;
    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        return null;
    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public List<Odontologo> buscarTodos() {
        return null;
    }

    @Override
    public void mostrarTodos() {

    }
}
