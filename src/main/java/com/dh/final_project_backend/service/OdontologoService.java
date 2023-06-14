package com.dh.final_project_backend.service;

import com.dh.final_project_backend.entity.Odontologo;
import com.dh.final_project_backend.repository.IDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService {

    private IDao<Odontologo> odontologoDao;

    public OdontologoService(IDao<Odontologo> odontologoDao) {
        this.odontologoDao = odontologoDao;
    }

    public IDao<Odontologo> getOdontologoDao() {
        return odontologoDao;
    }

    public void setOdontologoDao(IDao<Odontologo> odontologoDao) {
        this.odontologoDao = odontologoDao;
    }

    public Odontologo guardar(Odontologo odontologo){
        return odontologoDao.guardar(odontologo);
    }

    public Odontologo buscar(Integer id){
        return odontologoDao.buscar(id);
    }

    public Odontologo actualizar(Odontologo odontologo){
        return odontologoDao.actualizar(odontologo);
    }

    public void eliminar(Integer id){
        odontologoDao.eliminar(id);
    }

    public List<Odontologo> buscarTodos(){
        return odontologoDao.buscarTodos();
    }

}
