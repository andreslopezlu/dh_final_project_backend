package com.dh.final_project_backend.service;

import com.dh.final_project_backend.entity.Paciente;
import com.dh.final_project_backend.repository.IDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    private IDao<Paciente> pacienteDao;

    public PacienteService(IDao<Paciente> pacienteDao) {
        this.pacienteDao = pacienteDao;
    }

    public IDao<Paciente> getPacientDao() {
        return pacienteDao;
    }

    public void setPacientDao(IDao<Paciente> pacientDao) {
        this.pacienteDao = pacientDao;
    }

    public Paciente guardar(Paciente paciente){
        return pacienteDao.guardar(paciente);
    }

    public Paciente buscar(Integer id){
        return pacienteDao.buscar(id);
    }

    public Paciente actualizar(Paciente paciente){
        return pacienteDao.actualizar(paciente);
    }


    public void eliminar(Integer id){
        pacienteDao.eliminar(id);
    }

    public List<Paciente> buscarTodos(){
        return pacienteDao.buscarTodos();
    }

}
