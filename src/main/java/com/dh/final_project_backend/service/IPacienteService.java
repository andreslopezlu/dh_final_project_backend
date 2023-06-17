package com.dh.final_project_backend.service;

import com.dh.final_project_backend.entity.Paciente;
import com.dh.final_project_backend.entity.PacienteDTO;

import java.util.Set;

public interface IPacienteService {

    Paciente guardar(PacienteDTO pacienteDTO);
    PacienteDTO buscar(Long id);
    Paciente actualizar(PacienteDTO pacienteDTO);
    void eliminar(Long id);
    Set<PacienteDTO> buscarTodos();

}
