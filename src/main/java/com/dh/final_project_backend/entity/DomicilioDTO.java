package com.dh.final_project_backend.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DomicilioDTO {

    private Long id;
    private String calle;
    private Integer numero;
    private String provincia;
    private String pais;
    private Integer codigoPostal;
    private Paciente paciente;
}
