package com.dh.final_project_backend.service;

import com.dh.final_project_backend.entity.Turno;
import com.dh.final_project_backend.repository.IDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService {

    private IDao<Turno> turnoDao;

    public TurnoService() {
    }

    public IDao<Turno> getTurnoDao() {
        return turnoDao;
    }

    public void setTurnoDao(IDao<Turno> turnoDao) {
        this.turnoDao = turnoDao;
    }


    public Turno guardarTurno(Turno turno){
        return turnoDao.guardar(turno);
    }

    public Turno buscarTurno (Integer id){
        return turnoDao.buscar(id);
    }
    ``
    public Turno actualizarTurno (Turno turno){
        return turnoDao.actualizar(turno);
    }

    public void eliminarTurno (Integer id){
        turnoDao.eliminar(id);
    }

    public List<Turno> buscarTodosTurnos(){
        return turnoDao.buscarTodos();
    }

    public void mostrarTodosTurnos(){
        turnoDao.mostrarTodos();
    }

}
