package com.dh.final_project_backend.repository.impl;

import com.dh.final_project_backend.entity.Paciente;
import com.dh.final_project_backend.repository.IDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PacienteDaoH2 implements IDao<Paciente> {
    @Override
    public Paciente guardar(Paciente paciente) {
        return null;
    }

    @Override
    public Paciente buscar(Integer id) {
        return null;
    }

    @Override
    public Paciente actualizar(Paciente paciente) {
        return null;
    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public List<Paciente> buscarTodos() {
        return null;
    }

}
