package com.dh.final_project_backend.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Pacientes")
@Getter
@Setter
@ToString
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id", nullable=false)
    private Long id;

    private String nombre;

    private String apellido;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id")
    private Domicilio domicilio;

    private Integer dni;

    private LocalDate fechaAlta;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Turno> turnos = new HashSet<>();

    public Paciente() {
    }

//    public Paciente(String nombre, String apellido, Domicilio domicilio, Integer dni, LocalDate fechaAlta, Set<Turno> turnos) {
//        this.nombre = nombre;
//        this.apellido = apellido;
//        this.domicilio = domicilio;
//        this.dni = dni;
//        this.fechaAlta = fechaAlta;
//        this.turnos = turnos;
//    }
//
//    public Paciente(Long id, String nombre, String apellido, Domicilio domicilio, Integer dni, LocalDate fechaAlta, Set<Turno> turnos) {
//        this.id = id;
//        this.nombre = nombre;
//        this.apellido = apellido;
//        this.domicilio = domicilio;
//        this.dni = dni;
//        this.fechaAlta = fechaAlta;
//        this.turnos = turnos;
//    }
}
