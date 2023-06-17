package com.dh.final_project_backend.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class OdontologoDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String matricula;
    private Set<Turno> turnos;

}
