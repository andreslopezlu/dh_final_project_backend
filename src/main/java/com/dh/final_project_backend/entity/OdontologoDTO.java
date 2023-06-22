package com.dh.final_project_backend.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

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

}
