package com.dh.final_project_backend.repository.impl;

import com.dh.final_project_backend.entity.Turno;
import com.dh.final_project_backend.repository.IDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TurnoDaoH2 implements IDao<Turno> {
    @Override
    public Turno guardar(Turno turno) {
        return null;
    }

    @Override
    public Turno buscar(Integer id) {
        return null;
    }

    @Override
    public Turno actualizar(Turno turno) {
        return null;
    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public List<Turno> buscarTodos() {
        return null;
    }

    @Override
    public void mostrarTodos() {

    }
}
