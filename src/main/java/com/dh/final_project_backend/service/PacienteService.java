package com.dh.final_project_backend.service;

import com.dh.final_project_backend.entity.Paciente;
import com.dh.final_project_backend.repository.IDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    private IDao<Paciente> pacienteDao;

    public PacienteService() {
    }

    public IDao<Paciente> getPacientDao() {
        return pacienteDao;
    }

    public void setPacientDao(IDao<Paciente> pacientDao) {
        this.pacienteDao = pacientDao;
    }

    public Paciente guardarPaciente(Paciente paciente){
        return pacienteDao.guardar(paciente);
    }

    public Paciente buscarPaciente (Integer id){
        return pacienteDao.buscar(id);
    }

    public Paciente actualizarPaciente (Paciente paciente){
        return pacienteDao.actualizar(paciente);
    }


    public void eliminarPaciente (Integer id){
        pacienteDao.eliminar(id);
    }

    public List<Paciente> buscarTodosPacientes(){
        return pacienteDao.buscarTodos();
    }

    public void mostrarTodosPacientes(){
        pacienteDao.mostrarTodos();
    }

}
