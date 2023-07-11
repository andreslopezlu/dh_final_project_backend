package com.dh.final_project_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties({"hibernateLazyInitializer", "ignoreUnknown = true"})
public class PacienteDTO {

    private Long id;

    private String nombre;

    private String apellido;

    private Domicilio domicilio;

    private Integer dni;

    private LocalDate fechaAlta;

//    private Set<Turno> turnos;


    public PacienteDTO(String nombre, String apellido, Domicilio domicilio, Integer dni, LocalDate fechaAlta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.dni = dni;
        this.fechaAlta = fechaAlta;
    }
}
