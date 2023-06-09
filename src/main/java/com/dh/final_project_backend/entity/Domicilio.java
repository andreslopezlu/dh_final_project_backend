package com.dh.final_project_backend.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name="Domicilios")
@Getter
@Setter
@ToString
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id", nullable=false)
    private Long id;

    private String calle;

    private Integer numero;

    private String provincia;

    private String pais;

    private Integer codigoPostal;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "domicilio")
    @JsonIgnore
    private Paciente paciente;

    public Domicilio() {
    }

//    public Domicilio(String calle, Integer numero, String provincia, String pais, Integer codigoPostal, Paciente paciente) {
//        this.calle = calle;
//        this.numero = numero;
//        this.provincia = provincia;
//        this.pais = pais;
//        this.codigoPostal = codigoPostal;
//        this.paciente = paciente;
//    }
//
//    public Domicilio(Long id, String calle, Integer numero, String provincia, String pais, Integer codigoPostal, Paciente paciente) {
//        this.id = id;
//        this.calle = calle;
//        this.numero = numero;
//        this.provincia = provincia;
//        this.pais = pais;
//        this.codigoPostal = codigoPostal;
//        this.paciente = paciente;
//    }
}
