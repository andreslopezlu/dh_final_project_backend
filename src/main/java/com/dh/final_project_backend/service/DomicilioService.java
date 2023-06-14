package com.dh.final_project_backend.service;

import com.dh.final_project_backend.entity.Domicilio;
import com.dh.final_project_backend.repository.IDao;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomicilioService {
    private IDao<Domicilio> domicilioDao;
    public DomicilioService(IDao<Domicilio> domicilioDao) {
        this.domicilioDao = domicilioDao;
    }
    public IDao<Domicilio> getDomicilioDao() {
        return domicilioDao;
    }
    public void setDomicilioDao(IDao<Domicilio> domicilioDao) {
        this.domicilioDao = domicilioDao;
    }
    public Domicilio guardar(Domicilio domicilio){
        return domicilioDao.guardar(domicilio);
    };
    public Domicilio buscar(Integer id){
        return domicilioDao.buscar(id);
    };
    public Domicilio actualizar(Domicilio domicilio){
        return domicilioDao.actualizar(domicilio);
    };
    public void eliminar(Integer id){
        domicilioDao.eliminar(id);
    };
    public List<Domicilio> buscarTodos(){
        return domicilioDao.buscarTodos();
    };
}
