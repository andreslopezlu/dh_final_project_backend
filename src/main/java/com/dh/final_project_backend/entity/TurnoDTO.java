package com.dh.final_project_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties({"hibernateLazyInitializer", "ignoreUnknown = true"})
public class TurnoDTO {

    private Long id;

    private Paciente paciente;

    private Odontologo odontologo;

    private LocalDate fecha;

    private LocalTime hora;

}
