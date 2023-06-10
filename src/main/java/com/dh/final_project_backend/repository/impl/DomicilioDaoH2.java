package com.dh.final_project_backend.repository.impl;

import com.dh.final_project_backend.entity.Domicilio;
import com.dh.final_project_backend.repository.IDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DomicilioDaoH2 implements IDao<Domicilio> {
    @Override
    public Domicilio guardar(Domicilio domicilio) {
        return null;
    }

    @Override
    public Domicilio buscar(Integer id) {
        return null;
    }

    @Override
    public Domicilio actualizar(Domicilio domicilio) {
        return null;
    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public List<Domicilio> buscarTodos() {
        return null;
    }

    @Override
    public void mostrarTodos() {

    }
}
