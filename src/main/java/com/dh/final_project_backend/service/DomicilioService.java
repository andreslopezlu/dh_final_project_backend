package com.dh.final_project_backend.service;

import com.dh.final_project_backend.entity.Domicilio;
import com.dh.final_project_backend.repository.IDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomicilioService {

    private IDao<Domicilio> domicilioDao;

    public DomicilioService() {
    }

    public IDao<Domicilio> getDomicilioDao() {
        return domicilioDao;
    }

    public void setDomicilioDao(IDao<Domicilio> domicilioDao) {
        this.domicilioDao = domicilioDao;
    }

    public Domicilio guardarDomicilio(Domicilio domicilio){
        return domicilioDao.guardar(domicilio);
    };
    public Domicilio buscarDomicilio (Integer id){
        return domicilioDao.buscar(id);
    };
    public Domicilio actualizarDomicilio (Domicilio domicilio){
        return domicilioDao.actualizar(domicilio);
    };
    public void eliminarDomicilio (Integer id){
        domicilioDao.eliminar(id);
    };
    public List<Domicilio> buscarTodosDomicilios(){
        return domicilioDao.buscarTodos();
    };
    public void mostrarTodosDomicilios(){
        domicilioDao.mostrarTodos();
    };

}
