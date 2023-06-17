package com.dh.final_project_backend.service;

import com.dh.final_project_backend.entity.Domicilio;
import com.dh.final_project_backend.entity.DomicilioDTO;

import java.util.Set;

public interface IDomicilioService {

    Domicilio guardar(DomicilioDTO domicilioDTO);
    DomicilioDTO buscar(Long id);
    Domicilio actualizar(DomicilioDTO domicilioDTO);
    void eliminar(Long id);
    Set<DomicilioDTO> buscarTodos();

}
