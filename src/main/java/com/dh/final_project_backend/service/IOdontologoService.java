package com.dh.final_project_backend.service;

import com.dh.final_project_backend.entity.Odontologo;
import com.dh.final_project_backend.entity.OdontologoDTO;

import java.util.Set;

public interface IOdontologoService {

    Odontologo guardar(OdontologoDTO odontologoDTO);
    OdontologoDTO buscar(Long id);
    Odontologo actualizar(OdontologoDTO odontologoDTO);
    void eliminar(Long id);
    Set<OdontologoDTO> buscarTodos();

}
