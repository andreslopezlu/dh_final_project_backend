package com.dh.final_project_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties({"hibernateLazyInitializer", "ignoreUnknown = true"})
public class OdontologoDTO {

    private Long id;

    private String nombre;

    private String apellido;

    private String matricula;

//    private Set<Turno> turnos;


    public OdontologoDTO(String nombre, String apellido, String matricula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
    }
}
