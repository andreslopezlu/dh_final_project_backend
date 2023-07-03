package com.dh.final_project_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties({"hibernateLazyInitializer", "ignoreUnknown = true"})
public class DomicilioDTO {

    private Long id;

    private String calle;

    private Integer numero;

    private String provincia;

    private String pais;

    private Integer codigoPostal;
//    private Paciente paciente;
}
