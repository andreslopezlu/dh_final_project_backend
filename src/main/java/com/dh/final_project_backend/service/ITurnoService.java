package com.dh.final_project_backend.service;

import com.dh.final_project_backend.entity.Turno;
import com.dh.final_project_backend.entity.TurnoDTO;

import java.util.Set;

public interface ITurnoService {

    Turno guardar(TurnoDTO turnoDTO);
    TurnoDTO buscar(Long id);
    Turno actualizar(TurnoDTO turnoDTO);
    void eliminar(Long id);
    Set<TurnoDTO> buscarTodos();

}
