package com.dh.final_project_backend.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class PacienteDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private Domicilio domicilio;
    private Integer dni;
    private LocalDate fechaAlta;
    private Set<Turno> turnos;

}
