package com.dh.final_project_backend.service;

import com.dh.final_project_backend.entity.Turno;
import com.dh.final_project_backend.repository.IDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService {

    private IDao<Turno> turnoDao;

    public TurnoService(IDao<Turno> turnoDao) {
        this.turnoDao = turnoDao;
    }

    public IDao<Turno> getTurnoDao() {
        return turnoDao;
    }

    public void setTurnoDao(IDao<Turno> turnoDao) {
        this.turnoDao = turnoDao;
    }


    public Turno guardar(Turno turno){
        return turnoDao.guardar(turno);
    }

    public Turno buscar(Integer id){
        return turnoDao.buscar(id);
    }

    public Turno actualizar(Turno turno){
        return turnoDao.actualizar(turno);
    }

    public void eliminar(Integer id){
        turnoDao.eliminar(id);
    }

    public List<Turno> buscarTodos(){
        return turnoDao.buscarTodos();
    }

}
